USE MASTER
GO

CREATE DATABASE LeanSpooler
GO

CREATE LOGIN leanspooleruser WITH PASSWORD='lean@123@spooler@123@user', DEFAULT_DATABASE=LeanSpooler, CHECK_EXPIRATION=OFF
GO

USE LeanSpooler
GO

CREATE USER leanspooleruser FOR LOGIN leanspooleruser WITH DEFAULT_SCHEMA=dbo
GO

EXEC sp_addrolemember 'db_owner', 'leanspooleruser'
GO
