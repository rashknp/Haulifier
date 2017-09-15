package com.limitless.haulified.Haulifier.interfaces;

import com.limitless.haulified.Haulifier.webaccess.Response;

/**
 * interface to recieve the Retreived data
 */
public interface DataListener {
    /**
     * Method to respond when data got received from web-service.
     *
     * @param data
     */
    public void dataRetreived(Response data);

}
