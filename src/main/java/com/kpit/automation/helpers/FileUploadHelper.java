/**
 * This class provides methods for uploading files using Robot class.
 * It uses Robot class to simulate keyboard and mouse events.
 *
 * @author Snehal Karande
 * @version 1.0
 * @since 2023-12-01
 */
package com.kpit.automation.helpers;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;

import com.kpit.automation.utilities.LoggerUtility;

/**
 * This class extends {@link LoggerUtility} to provide logging functionality.
 *
 * @author Snehal Karande
 * @version 1.0
 * @since 2023-12-01
 */
public class FileUploadHelper extends LoggerUtility {

	/**
	 * The Logger instance for this class.
	 */
	private Logger log = getLogger(FileUploadHelper.class);

	/**
	 * This method uploads a file to the system by simulating keyboard and mouse
	 * events. It sets the clipboard with the provided file path and then simulates
	 * the Ctrl+V and Enter key presses.
	 *
	 * @param path The path of the file to be uploaded.
	 * @throws Exception If any exception occurs during the file upload process.
	 */
	public void fileUploadMethod(String path) throws Exception {
		try {
			Robot rb = new Robot();
			rb.delay(2000); // Delay to ensure previous action is completed
			StringSelection ss = new StringSelection(path); // Creating StringSelection with file path
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null); // Setting clipboard content

			rb.keyPress(KeyEvent.VK_CONTROL); // Simulating Ctrl key press
			rb.keyPress(KeyEvent.VK_V); // Simulating V key press

			rb.keyRelease(KeyEvent.VK_CONTROL); // Releasing Ctrl key
			rb.keyRelease(KeyEvent.VK_V); // Releasing V key

			rb.keyPress(KeyEvent.VK_ENTER); // Simulating Enter key press
			rb.keyRelease(KeyEvent.VK_ENTER); // Releasing Enter key

		} catch (Exception ex) {
			ex.printStackTrace(); // Printing stack trace if an exception occurs
			log.error(ex); // Logging the exception
		}
	}
}
