/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gdgvit.apps.gdd17.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.gdgvit.apps.gdd17.service.SessionAlarmService;
import com.google.gdgvit.apps.gdd17.service.SessionCalendarService;
import com.google.gdgvit.apps.gdd17.util.LogUtils;

import static com.google.gdgvit.apps.gdd17.util.LogUtils.makeLogTag;

/**
 * {@link BroadcastReceiver} to reinitialize {@link android.app.AlarmManager} for all starred
 * session blocks.
 */
public class SessionAlarmReceiver extends BroadcastReceiver {
    public static final String TAG = LogUtils.makeLogTag(SessionAlarmReceiver.class);

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (SessionCalendarService.ACTION_UPDATE_ALL_SESSIONS_CALENDAR_COMPLETED.equals(action)
                || Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Intent scheduleIntent = new Intent(
                    SessionAlarmService.ACTION_SCHEDULE_ALL_STARRED_BLOCKS,
                    null, context, SessionAlarmService.class);
            context.startService(scheduleIntent);
        }
    }
}
