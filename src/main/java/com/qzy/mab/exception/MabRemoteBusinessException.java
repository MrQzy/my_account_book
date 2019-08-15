package com.qzy.mab.exception;

import java.io.Serializable;

/**
 * @author qzy
 */
public class MabRemoteBusinessException extends Exception {

    private static final long serialVersionUID = -3635470119368741502L;
    private Serializable remoteBusinessException;

    public MabRemoteBusinessException() {
    }

    public MabRemoteBusinessException(String s) {
        super(s);
    }

    public MabRemoteBusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MabRemoteBusinessException(Serializable remoteBusinessException, String message) {
        super(remoteBusinessException + " " + message);
        this.remoteBusinessException = remoteBusinessException;
    }

    public Serializable getMabRemoteBusinesException() {
        return this.remoteBusinessException;
    }
}
