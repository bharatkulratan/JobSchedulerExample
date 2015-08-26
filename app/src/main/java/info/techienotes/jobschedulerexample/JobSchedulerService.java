package info.techienotes.jobschedulerexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * @author Bharat Kul Ratan
 */
public class JobSchedulerService extends JobService{

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this, "Job started.", Toast.LENGTH_SHORT).show();
        new JobAsyncTask().execute(jobParameters);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Toast.makeText(this, "Job Stopped.", Toast.LENGTH_SHORT).show();
        return false;
    }

    private class JobAsyncTask extends AsyncTask <JobParameters, Void, JobParameters> {
        // JobParameters contains the parameters used to configure/identify the job.
        // You do not create this object yourself,
        // instead it is handed in to your application by the System.

        @Override
        protected JobParameters doInBackground(JobParameters... params) {
            //Wait for 2 seconds to finish dummy task
            SystemClock.sleep(2000);
            return params[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters) {
            jobFinished(jobParameters, false);
            Toast.makeText(getBaseContext(), "Task Finished", Toast.LENGTH_SHORT).show();
        }
    }
}
