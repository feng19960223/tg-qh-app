package com.turingoal.qh.bean;

import lombok.Data;

/**
 * EventBus事件类
 */

@Data
public class MessageEvent {
    private String key;
    private String message;

    public MessageEvent(String key, String message) {
        this.key = key;
        this.message = message;
    }
}
