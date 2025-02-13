package com.navercorp.pinpoint.web.controller;

import com.navercorp.pinpoint.web.response.ErrorResponse;
import com.navercorp.pinpoint.web.response.Response;
import com.navercorp.pinpoint.web.response.SuccessResponse;
import com.navercorp.pinpoint.web.response.WebhookResponse;
import com.navercorp.pinpoint.web.service.WebhookService;
import com.navercorp.pinpoint.web.vo.Webhook;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value={"/webhook", "/application/webhook"})
public class WebhookController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public final static String APPLICATION_ID = "applicationId";
    public final static String SERVICE_NAME = "serviceName";
    public final static String ALARM_RULE_ID = "ruleId";

    private final WebhookService webhookService;

    @Value("${webhook.enable:false}")
    private boolean webhookEnable;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = Objects.requireNonNull(webhookService, "webhookService");
    }

    @PostMapping()
    public ResponseEntity<Response> insertWebhook(@RequestBody Webhook webhook) {
        if (!webhookEnable) {
            return ErrorResponse.serverError("webhook function is disabled");
        }

        if (!StringUtils.hasText(webhook.getUrl()) || !(StringUtils.hasText(webhook.getApplicationId())
                || StringUtils.hasText(webhook.getServiceName()))) {
            return ErrorResponse.serverError("there should be url, applicationId/serviceName to insert webhook");
        }
        String webhookId = webhookService.insertWebhook(webhook);
        return ResponseEntity.ok(new WebhookResponse("SUCCESS", webhookId));
    }

    @DeleteMapping()
    public ResponseEntity<Response> deleteWebhook(@RequestBody Webhook webhook) {
        if (!webhookEnable) {
            return ErrorResponse.serverError("webhook function is disabled");
        }

        if (!StringUtils.hasText(webhook.getWebhookId())) {
            return ErrorResponse.badRequest("there should be webhookId to delete webhook");
        }
        webhookService.deleteWebhook(webhook);
        return SuccessResponse.ok();
    }

    @GetMapping()
    public Object getWebhook(@RequestParam(value=APPLICATION_ID, required=false) String applicationId,
                             @RequestParam(value=SERVICE_NAME, required=false) String serviceName,
                             @RequestParam(value=ALARM_RULE_ID, required=false) String ruleId) {
        if (!webhookEnable) {
            return ErrorResponse.serverError("webhook function is disabled");
        }

        if (!StringUtils.hasText(applicationId) && !StringUtils.hasText(serviceName) && !StringUtils.hasText(ruleId)) {
            return ErrorResponse.badRequest("applicationId / serviceName / ruleId is needed to get webhooks");
        }

        if (StringUtils.hasText(ruleId)) {
            return webhookService.selectWebhookByRuleId(ruleId);
        }

        if (StringUtils.hasText(applicationId)) {
            return webhookService.selectWebhookByApplicationId(applicationId);
        }

        return webhookService.selectWebhookByServiceName(serviceName);
    }

    @PutMapping()
    public ResponseEntity<Response> updateWebhook(@RequestBody Webhook webhook) {
        if (!webhookEnable) {
            return ErrorResponse.serverError("webhook function is disabled");
        }
        
        if (!StringUtils.hasText(webhook.getWebhookId()) || !StringUtils.hasText(webhook.getUrl()) ||
                !(StringUtils.hasText(webhook.getApplicationId()) || StringUtils.hasText(webhook.getServiceName()))) {
            return ErrorResponse.badRequest("there should be webhookId, url, applicationId/serviceName to update webhook");
        }
        webhookService.updateWebhook(webhook);
        return SuccessResponse.ok();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        logger.warn(" Exception occurred while trying to CRUD Webhook information", e);
        return ErrorResponse.serverError(String.format("Exception occurred while trying to process Webhook information: %s", e.getMessage()));
    }
}
