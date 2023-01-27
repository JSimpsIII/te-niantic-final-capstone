-- The following line terminates any active connections to the database so that it can be dropped
SELECT pg_terminate_backend(pid) 
FROM pg_stat_activity 
WHERE datname = 'workout_scheduler';

DROP DATABASE IF EXISTS workout_scheduler;

DROP USER IF EXISTS workout_scheduler_owner;
DROP USER IF EXISTS workout_scheduler_appuser;

-- then create the database
CREATE DATABASE workout_scheduler;
