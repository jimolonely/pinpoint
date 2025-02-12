/*
 * Copyright 2022 NAVER Corp.
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
package com.navercorp.pinpoint.plugin.elasticsearch8;

import com.navercorp.pinpoint.common.trace.AnnotationKey;
import com.navercorp.pinpoint.common.trace.AnnotationKeyFactory;
import com.navercorp.pinpoint.common.trace.AnnotationKeyProperty;
import com.navercorp.pinpoint.common.trace.ServiceType;
import com.navercorp.pinpoint.common.trace.ServiceTypeProvider;

public class Elasticsearch8Constants {

    public static final AnnotationKey ARGS_DSL_ANNOTATION_KEY = AnnotationKeyFactory.of(177, "es8.dsl", AnnotationKeyProperty.VIEW_IN_RECORD_SET);
    public static final AnnotationKey ARGS_VERSION_ANNOTATION_KEY = AnnotationKeyFactory.of(178, "es8.version", AnnotationKeyProperty.VIEW_IN_RECORD_SET);

    public static final ServiceType ELASTICSEARCH = ServiceTypeProvider.getByName("ELASTICSEARCH8");
    public static final ServiceType ELASTICSEARCH_EXECUTOR = ServiceTypeProvider.getByName("ELASTICSEARCH8_CLIENT");
}
