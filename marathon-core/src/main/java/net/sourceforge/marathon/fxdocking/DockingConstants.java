/*******************************************************************************
 * Copyright 2016 Jalian Systems Pvt. Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sourceforge.marathon.fxdocking;

import java.util.logging.Logger;

import javafx.geometry.Orientation;

public class DockingConstants {

    public static final Logger LOGGER = Logger.getLogger(DockingConstants.class.getName());

    public enum Split {
        LEFT(Orientation.HORIZONTAL), RIGHT(Orientation.HORIZONTAL), TOP(Orientation.VERTICAL), BOTTOM(Orientation.VERTICAL);
        private Orientation orientation;

        Split(Orientation orientation) {
            this.orientation = orientation;
        }

        public Orientation getOrientation() {
            return orientation;
        }
    }

    public static class Hide {

        private int value;

        private Hide(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

}
