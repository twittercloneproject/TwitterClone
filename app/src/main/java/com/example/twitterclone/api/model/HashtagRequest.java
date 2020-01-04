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


public class HashtagRequest {
    /**
     * a hashtag
     */
    @com.google.gson.annotations.SerializedName("hashtag")
    private String hashtag = null;
    @com.google.gson.annotations.SerializedName("lastDate")
    private String lastDate = null;

    /**
     * a hashtag
     *
     * @return hashtag
     **/
    public String getHashtag() {
        return hashtag;
    }

    /**
     * Sets the value of hashtag.
     *
     * @param hashtag the new value
     */
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    /**
     * Gets lastDate
     *
     * @return lastDate
     **/
    public String getLastDate() {
        return lastDate;
    }

    /**
     * Sets the value of lastDate.
     *
     * @param lastDate the new value
     */
    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

}
