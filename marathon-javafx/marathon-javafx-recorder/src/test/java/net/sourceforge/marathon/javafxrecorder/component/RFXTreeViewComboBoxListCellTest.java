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
package net.sourceforge.marathon.javafxrecorder.component;

import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxTreeCell;
import javafx.scene.layout.Pane;
import net.sourceforge.marathon.javafx.tests.ComboBoxTreeViewSample;
import net.sourceforge.marathon.javafxrecorder.component.LoggingRecorder.Recording;

public class RFXTreeViewComboBoxListCellTest extends RFXComponentTest {

    @Test
    public void select() {
        @SuppressWarnings("unchecked")
        TreeView<String> treeView = (TreeView<String>) getPrimaryStage().getScene().getRoot().lookup(".tree-view");
        LoggingRecorder lr = new LoggingRecorder();
        Platform.runLater(() -> {
            @SuppressWarnings("unchecked")
            ComboBoxTreeCell<String> cell = (ComboBoxTreeCell<String>) getCellAt(treeView, 1);
            Point2D point = getPoint(treeView, 1);
            RFXTreeView rfxTreeView = new RFXTreeView(treeView, null, point, lr);
            rfxTreeView.focusGained(rfxTreeView);
            cell.startEdit();
            cell.updateItem("Option 3", false);
            cell.commitEdit("Option 3");
            rfxTreeView.focusLost(rfxTreeView);
        });
        List<Recording> recordings = lr.waitAndGetRecordings(1);
        Recording recording = recordings.get(0);
        AssertJUnit.assertEquals("recordSelect", recording.getCall());
        AssertJUnit.assertEquals("Option 3", recording.getParameters()[0]);
    }

    @Test
    public void selectEditable() {
        @SuppressWarnings("unchecked")
        TreeView<String> treeView = (TreeView<String>) getPrimaryStage().getScene().getRoot().lookup(".tree-view");
        LoggingRecorder lr = new LoggingRecorder();
        Platform.runLater(() -> {
            @SuppressWarnings("unchecked")
            ComboBoxTreeCell<String> cell = (ComboBoxTreeCell<String>) getCellAt(treeView, 1);
            cell.setComboBoxEditable(true);
            Point2D point = getPoint(treeView, 1);
            RFXTreeView rfxtreeView = new RFXTreeView(treeView, null, point, lr);
            rfxtreeView.focusGained(rfxtreeView);
            cell.startEdit();
            cell.updateItem("Option 5", false);
            cell.commitEdit("Option 5");
            rfxtreeView.focusLost(rfxtreeView);
        });
        List<Recording> recordings = lr.waitAndGetRecordings(1);
        Recording recording = recordings.get(0);
        AssertJUnit.assertEquals("recordSelect", recording.getCall());
        AssertJUnit.assertEquals("Option 5", recording.getParameters()[0]);
    }

    @Override
    protected Pane getMainPane() {
        return new ComboBoxTreeViewSample();
    }
}
