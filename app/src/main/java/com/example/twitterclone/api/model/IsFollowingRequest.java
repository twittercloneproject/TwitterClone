/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.example.twitterclone.api.model;


public class IsFollowingRequest {
    /**
     * alias of current user
     */
    @com.google.gson.annotations.SerializedName("currentAlias")
    private String currentAlias = null;
    /**
     * alias of use to check
     */
    @com.google.gson.annotations.SerializedName("otherAlias")
    private String otherAlias = null;

    /**
     * alias of current user
     *
     * @return currentAlias
     **/
    public String getCurrentAlias() {
        return currentAlias;
    }

    /**
     * Sets the value of currentAlias.
     *
     * @param currentAlias the new value
     */
    public void setCurrentAlias(String currentAlias) {
        this.currentAlias = currentAlias;
    }

    /**
     * alias of use to check
     *
     * @return otherAlias
     **/
    public String getOtherAlias() {
        return otherAlias;
    }

    /**
     * Sets the value of otherAlias.
     *
     * @param otherAlias the new value
     */
    public void setOtherAlias(String otherAlias) {
        this.otherAlias = otherAlias;
    }

}
