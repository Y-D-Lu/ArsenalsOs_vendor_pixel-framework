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

package com.google.android.systemui.statusbar.dagger

import com.android.systemui.CoreStartable
import com.android.systemui.statusbar.phone.CentralSurfaces
import com.google.android.systemui.statusbar.phone.CentralSurfacesGoogle
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface StartCentralSurfacesGoogleModule {
    /** Start the CentralSurfaces   */
    @Binds
    @IntoMap
    @ClassKey(CentralSurfaces::class)
    abstract fun bindsCentralSurfaces(centralSurfaces: CentralSurfacesGoogle): CoreStartable
}
