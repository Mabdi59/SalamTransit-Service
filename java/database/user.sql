-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER salamtransit_owner
WITH PASSWORD 'salamtransitpass';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO salamtransit_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO salamtransit_owner;

CREATE USER salamtransit_appuser
WITH PASSWORD 'salamtransitpass';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO salamtransit_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO salamtransit_appuser;