/*
 * Copyright (C) 2022 The PixelExperience Project
 * Copyright (C) 2023 Arsenals Os
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

package com.google.android.systemui.qs.dagger;

import static com.android.systemui.qs.dagger.QSFlagsModule.RBC_AVAILABLE;

import android.content.Context;
import android.hardware.display.NightDisplayListener;
import android.os.Handler;

import com.android.systemui.dagger.qualifiers.Background;
import com.android.systemui.media.dagger.MediaModule;
import com.android.systemui.qs.AutoAddTracker;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSTileHost;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.qs.dagger.QSFlagsModule;
import com.android.systemui.qs.dagger.QSFragmentComponent;
import com.android.systemui.qs.external.QSExternalModule;
import com.android.systemui.statusbar.phone.AutoTileManager;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.DeviceControlsController;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.SafetyController;
import com.android.systemui.statusbar.policy.WalletController;
import com.android.systemui.util.settings.SecureSettings;

import com.google.android.systemui.statusbar.phone.AutoTileManagerGoogle;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Module for QS dependencies
 */
@Module(subcomponents = {QSFragmentComponent.class},
        includes = {MediaModule.class, QSExternalModule.class, QSFlagsModule.class})
public interface QSModuleGoogle {

    @Provides
    static AutoTileManager provideAutoTileManager(
            Context context,
            AutoAddTracker.Builder autoAddTrackerBuilder,
            QSTileHost host,
            @Background Handler handler,
            SecureSettings secureSettings,
            HotspotController hotspotController,
            DataSaverController dataSaverController,
            ManagedProfileController managedProfileController,
            NightDisplayListener nightDisplayListener,
            CastController castController,
            ReduceBrightColorsController reduceBrightColorsController,
            DeviceControlsController deviceControlsController,
            WalletController walletController,
            SafetyController safetyController,
            @Named(RBC_AVAILABLE) boolean isReduceBrightColorsAvailable,
            BatteryController batteryController) {
        AutoTileManager manager = new AutoTileManagerGoogle(
                context,
                autoAddTrackerBuilder,
                host,
                handler,
                secureSettings,
                hotspotController,
                dataSaverController,
                managedProfileController,
                nightDisplayListener,
                castController,
                reduceBrightColorsController,
                deviceControlsController,
                walletController,
                safetyController,
                isReduceBrightColorsAvailable,
                batteryController
        );
        manager.init();
        return manager;
    }

    /** */
    @Binds
    QSHost provideQsHost(QSTileHost controllerImpl);
}
