package main;

import processing.core.PApplet;

public interface OnMessageListener {

	void OnMessage(String msg, String ip, String port);

}
