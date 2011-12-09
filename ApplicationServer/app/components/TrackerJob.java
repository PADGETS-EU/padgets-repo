package components;

import play.Logger;
import play.jobs.Every;
import play.jobs.Job;

@Every("300s")
public class TrackerJob extends Job{
	
	public void doJob() {
		Logger.debug("");
		Tracker.track("blogger");
		Tracker.track("facebook");
//		Tracker.track("twitter");
	}
}
