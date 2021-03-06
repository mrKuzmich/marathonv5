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
package net.sourceforge.marathon.javadriver.cmdlinelauncher;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.swingset3.SwingSet3;

import net.sourceforge.marathon.javadriver.ClassPathHelper;
import net.sourceforge.marathon.javadriver.JavaDriver;
import net.sourceforge.marathon.javadriver.JavaProfile;
import net.sourceforge.marathon.javadriver.JavaProfile.LaunchMode;

@Test
public class LaunchJavaCommandLineTest {

    private JavaDriver driver;

    public LaunchJavaCommandLineTest() {
    }

    @SuppressWarnings("unused")
    @BeforeClass
    public void createDriver() {
        if (true) {
            throw new SkipException("LaunchJavaCommandLineTest skipped due to bin folder is used as javaagent instead of jar");
        }
        JavaProfile profile = new JavaProfile(LaunchMode.JAVA_COMMAND_LINE);
        File f = findFile();
        profile.addClassPath(f);
        profile.setMainClass("com.sun.swingset3.SwingSet3");
        DesiredCapabilities caps = new DesiredCapabilities("java", "1.5", Platform.ANY);
        driver = new JavaDriver(profile, caps, caps);
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    public void getDriverWithProfile() throws Throwable {
        List<WebElement> buttons = driver.findElements(By.cssSelector("toggle-button"));
        AssertJUnit.assertTrue(buttons.size() > 0);
        buttons.get(3).click();
        buttons.get(0).click();
    }

    private File findFile() {
        return new File(ClassPathHelper.getClassPath(SwingSet3.class));
    }

}
