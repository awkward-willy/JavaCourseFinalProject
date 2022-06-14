package JFrameGUI;

import javax.swing.JFileChooser;

import dataIO.BookDataIO;
import dataIO.UserDataIO;

public class ImportExportData {
	public ImportExportData(int mode) {
		// mode: 1 = import admin
		// 2 = import member
		// 3 = import book
		JFileChooser file = new JFileChooser(System.getProperty("user.dir"));
		file.setMultiSelectionEnabled(false);
		file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			java.io.File f = file.getSelectedFile();
			try {
				if (mode == 1) {
					UserDataIO.ReadAdminCSV(f.getPath());
				} else if (mode == 2) {
					UserDataIO.ReadMemberCSV(f.getPath());
				} else if (mode == 3) {
					BookDataIO.ReadCSV(f.getPath());
				}
			} catch (Exception e) {
			}
		}
	}
}
