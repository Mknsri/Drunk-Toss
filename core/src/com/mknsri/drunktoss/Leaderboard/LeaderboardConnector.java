/*
The MIT License (MIT)

Copyright (c) 2015 Markus Mikonsaari

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.mknsri.drunktoss.Leaderboard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mknsri.drunktoss.DrunkToss;

public class LeaderboardConnector {
	static String leaderboardAddress = "XXXXXX";
	static String playerID = DrunkToss.prefs.getString("playerID");
	
	private String scoreResult;
	private boolean requestDone = false;
	private boolean requestStarted = false;
	private long timeOut = 0;
	public boolean timedOut = false;
	
	public JsonValue leaderBoardJsonTable = null;
	
	public LeaderboardConnector() {
	}
	
	public void insertHighScore(int score, String name) {

	}

	private void getHighScores(String scoreType) {

	}

	public String[][] getPersonalScores() {
		String[][] scoreTable = new String[10][3];

		return scoreTable;	
	}

	public String[][] getWeeklyScores() {
		String[][] scoreTable = new String[10][3];

		return scoreTable;		
	}

	public String[][] getAllTimeScores() {
		String[][] scoreTable = new String[10][3];

		
		return scoreTable;	
	}
}
