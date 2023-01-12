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

package UQAC.Mobile.SAMM.Base;

/**
 * Class that represents stuff that can be repaired during a maintenance
 */
public class Repair {
    // TODO : export string value + complete the list
    public static final String BATTERY = "Batterie";
    public static final String SPARK_PLUGS = "Bougies";
    public static final String SEAT_BELTS = "Ceintures de sécurité";
    public static final String OIL_CHANGE = "Changement d'huile";
    public static final String BRAKES_CHANGE = "Changement des freins";
    public static final String COOLING_SYSTEM = "Circuit de refroidissement";
    public static final String AIR_CONDITIONER = "Climatisation";
    public static final String CHECKUP = "Inspection";
    public static final String FRAME = "Chassis";
    public static final String TIMMING_BELT = "Courroie de distribution";
    public static final String ACCESSORY_BELT = "Courroie d'accessoire";
    public static final String LABOR = "Main d'oeuvre";

    private String name;
    private float price;
}
