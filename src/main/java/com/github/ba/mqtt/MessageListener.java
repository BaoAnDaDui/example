package com.github.ba.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang xiao
 * date 2022/11/14
 */
public class MessageListener implements IMqttMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MqttUtil.class);


    /**
     * 处理消息
     *
     * @param topic       主题
     * @param mqttMessage 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        logger.info(String.format("MQTT: 订阅主题[%s]发来消息[%s]" , topic, new String(mqttMessage.getPayload())));
    }

}