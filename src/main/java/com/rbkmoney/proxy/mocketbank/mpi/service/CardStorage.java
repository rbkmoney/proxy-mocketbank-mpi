package com.rbkmoney.proxy.mocketbank.mpi.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CardStorage {

    private static final Map<String, String> storage = new ConcurrentHashMap<>();

    public void save(String pan, String transactionId) {
        storage.put(transactionId, pan);
    }

    public String getCard(String transactionId) {
        return storage.get(transactionId);
    }

}
