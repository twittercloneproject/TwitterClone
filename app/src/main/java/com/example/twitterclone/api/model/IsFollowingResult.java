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


public class IsFollowingResult {
    /**
     * boolean of following
     */
    @com.google.gson.annotations.SerializedName("isFollowing")
    private Boolean isFollowing = null;

    /**
     * boolean of following
     *
     * @return isFollowing
     **/
    public Boolean getIsFollowing() {
        return isFollowing;
    }

    /**
     * Sets the value of isFollowing.
     *
     * @param isFollowing the new value
     */
    public void setIsFollowing(Boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

}
