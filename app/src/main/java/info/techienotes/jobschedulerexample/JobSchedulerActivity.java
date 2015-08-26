package info.techienotes.jobschedulerexample;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JobSchedulerActivity extends Activity {

    //Any int value would be fine
    private static final int JOB_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);

        Button scheduleJob = (Button) findViewById(R.id.button_scheduled_job);

     // JobInfo holds the constraints of when the job should run
     // (e.g. when connected to Wi-Fi, when plugged in, or at a certain period, etc.)
        final JobInfo job = new JobInfo.Builder(JOB_ID, new ComponentName(this, JobSchedulerService.class))
                .setRequiresCharging(true)  // Job will be started if device is plugged in
                .build();

        //Getting service required for JobScheduler
        final JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        scheduleJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobScheduler.schedule(job);
            }
        });
    }
}
