/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.plugin.mongo.interceptor;

import com.mongodb.WriteConcern;
import com.navercorp.pinpoint.bootstrap.context.DatabaseInfo;
import com.navercorp.pinpoint.bootstrap.interceptor.AroundInterceptor;
import com.navercorp.pinpoint.bootstrap.logging.PLogger;
import com.navercorp.pinpoint.bootstrap.logging.PLoggerFactory;
import com.navercorp.pinpoint.bootstrap.plugin.jdbc.DatabaseInfoAccessor;
import com.navercorp.pinpoint.bootstrap.plugin.jdbc.MongoDatabaseInfo;
import com.navercorp.pinpoint.bootstrap.plugin.jdbc.UnKnownDatabaseInfo;
import com.navercorp.pinpoint.plugin.mongo.MongoUtil;

/**
 * @author Roy Kim
 */
public class MongoWriteConcernInterceptor implements AroundInterceptor {

    private final PLogger logger = PLoggerFactory.getLogger(this.getClass());
    private final boolean isDebug = logger.isDebugEnabled();


    public MongoWriteConcernInterceptor() {
    }

    @Override
    public void before(Object target, Object[] args) {
        if (isDebug) {
            logger.beforeInterceptor(target, args);
        }
    }

    @Override
    public void after(Object target, Object[] args, Object result, Throwable throwable) {
        if (isDebug) {
            logger.afterInterceptor(target, args, result, throwable);
        }

        if (args == null) {
            return;
        }

        DatabaseInfo databaseInfo = DatabaseInfoUtils.getDatabaseInfo(target, UnKnownDatabaseInfo.MONGO_INSTANCE);

        String writeConcernStr = MongoUtil.getWriteConcern0((WriteConcern) args[0]);

        databaseInfo = new MongoDatabaseInfo(databaseInfo.getType(), databaseInfo.getExecuteQueryType()
                , databaseInfo.getRealUrl(), databaseInfo.getUrl(), databaseInfo.getHost(), databaseInfo.getDatabaseId()
                , ((MongoDatabaseInfo) databaseInfo).getCollectionName(), ((MongoDatabaseInfo) databaseInfo).getReadPreference(), writeConcernStr);

        if (result instanceof DatabaseInfoAccessor) {
            ((DatabaseInfoAccessor) result)._$PINPOINT$_setDatabaseInfo(databaseInfo);
        }
    }

}
