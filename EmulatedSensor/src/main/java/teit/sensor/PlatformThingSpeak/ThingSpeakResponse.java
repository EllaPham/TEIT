/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teit.sensor.PlatformThingSpeak;

import java.util.List;

/**
 *
 * @author Trang
 */
public class ThingSpeakResponse {
    private Channel channel;
    private List<Feed> feeds;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public Channel getChannel() {
        return channel;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
     
}
