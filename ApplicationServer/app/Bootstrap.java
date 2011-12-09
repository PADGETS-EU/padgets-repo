import java.util.ArrayList;
import java.util.List;

import models.OpenIDProvider;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {

	public void doJob() {
		List list = new ArrayList<Class>();
		// list.add(SMProvider.class);
		list.add(OpenIDProvider.class);
		// list.add(Topic.class);
		// list.add(Areacode.class);
		Fixtures.delete(list);
		Fixtures.loadModels("data.yml");

	}
}
