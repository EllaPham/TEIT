/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.main;

import java.io.File;
import java.util.TimerTask;

/**
 *
 * @author hungld
 */
public abstract class FileWatcherWithTimer extends TimerTask {

    private long timeStamp;
    private File file;

    public FileWatcherWithTimer(File file) {
        this.file = file;
        this.timeStamp = file.lastModified();
    }

    @Override
    public final void run() {
        long currentTimeStamp = file.lastModified();

        if (this.timeStamp != currentTimeStamp) {
            this.timeStamp = currentTimeStamp;
            onChange(file);
        }
    }

    protected abstract void onChange(File file);
}
