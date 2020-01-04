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
import com.example.twitterclone.api.model.StatusesResultStatusesItem;

public class StatusesResult {
    @com.google.gson.annotations.SerializedName("statuses")
    private List<StatusesResultStatusesItem> statuses = null;

    /**
     * Gets statuses
     *
     * @return statuses
     **/
    public List<StatusesResultStatusesItem> getStatuses() {
        return statuses;
    }

    /**
     * Sets the value of statuses.
     *
     * @param statuses the new value
     */
    public void setStatuses(List<StatusesResultStatusesItem> statuses) {
        this.statuses = statuses;
    }

}
