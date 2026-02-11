-- Migration to rename bastanteo column to POA in power_of_attorney table
-- This migration renames the column from is_bastanteo_completed to is_poa_completed
-- Only executes if the old column exists (for existing databases)

DO $$
BEGIN
    -- Check if the old column exists and rename it
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'power_of_attorney'
        AND column_name = 'is_bastanteo_completed'
    ) THEN
        ALTER TABLE power_of_attorney
        RENAME COLUMN is_bastanteo_completed TO is_poa_completed;

        RAISE NOTICE 'Column is_bastanteo_completed renamed to is_poa_completed';
    ELSE
        RAISE NOTICE 'Column is_bastanteo_completed does not exist, skipping rename';
    END IF;

    -- Add comment to the column for clarity (regardless of whether rename happened)
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'power_of_attorney'
        AND column_name = 'is_poa_completed'
    ) THEN
        EXECUTE 'COMMENT ON COLUMN power_of_attorney.is_poa_completed IS ''Indicates whether the Power of Attorney (POA) process has been completed''';
    END IF;
END $$;
