/*
 * Copyright 2022 - Hugo LANGLAIS & Alexia LACOMBE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package UQAC.Mobile.SAMM.API;

import java.util.List;

import UQAC.Mobile.SAMM.Base.Car;
import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.Base.Event;
import UQAC.Mobile.SAMM.Base.Refuel;

/**
 * NetworkCallbacks are calls after a request
 */
public class NetworkCallback {
    public void onActionSuccess() {
    }

    public void onActionSuccess(Car[] cars) {
    }

    public void onActionSuccess(Refuel[] refuels) {
    }

    public void onActionSuccess(Cost[] costs) {
    }

    public void onActionSuccess(Earning[] earnings) {
    }

    public void onActionSuccess(Event[] events) {
    }

    public void onActionSuccess(List<Event> events) {
    }

    public void onActionFailure() {
    }
}
