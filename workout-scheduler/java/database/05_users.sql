-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER workout_scheduler_owner
WITH PASSWORD 'workout_scheduler_pass';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO workout_scheduler_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO workout_scheduler_owner;

CREATE USER workout_scheduler_appuser
WITH PASSWORD 'workout_scheduler_pass';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO workout_scheduler_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO workout_scheduler_appuser;
