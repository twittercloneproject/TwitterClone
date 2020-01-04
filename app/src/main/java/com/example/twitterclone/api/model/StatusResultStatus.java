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

import java.util.*;

public class StatusResultStatus {
    /**
     * name
     */
    @com.google.gson.annotations.SerializedName("name")
    private String name = null;
    /**
     * message
     */
    @com.google.gson.annotations.SerializedName("message")
    private String message = null;
    @com.google.gson.annotations.SerializedName("hashtags")
    private List<String> hashtags = null;
    /**
     * alias
     */
    @com.google.gson.annotations.SerializedName("alias")
    private String alias = null;
    /**
     * date
     */
    @com.google.gson.annotations.SerializedName("date")
    private String date = null;
    /**
     * pic
     */
    @com.google.gson.annotations.SerializedName("urlPicture")
    private String urlPicture = null;
    @com.google.gson.annotations.SerializedName("id")
    private Integer id = null;
    @com.google.gson.annotations.SerializedName("profilePic")
    private String profilePic = null;

    /**
     * name
     *
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name.
     *
     * @param name the new value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * message
     *
     * @return message
     **/
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of message.
     *
     * @param message the new value
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets hashtags
     *
     * @return hashtags
     **/
    public List<String> getHashtags() {
        return hashtags;
    }

    /**
     * Sets the value of hashtags.
     *
     * @param hashtags the new value
     */
    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * alias
     *
     * @return alias
     **/
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of alias.
     *
     * @param alias the new value
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * date
     *
     * @return date
     **/
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of date.
     *
     * @param date the new value
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * pic
     *
     * @return urlPicture
     **/
    public String getUrlPicture() {
        return urlPicture;
    }

    /**
     * Sets the value of urlPicture.
     *
     * @param urlPicture the new value
     */
    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    /**
     * Gets id
     *
     * @return id
     **/
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of id.
     *
     * @param id the new value
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets profilePic
     *
     * @return profilePic
     **/
    public String getProfilePic() {
        return profilePic;
    }

    /**
     * Sets the value of profilePic.
     *
     * @param profilePic the new value
     */
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
