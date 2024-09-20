-- ��������� ��� ������� ������
DECLARE @Symbol CHAR(52)= 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz',
        @Position INT,
        @i INT,
        @NameLimit INT,
        @FullName NVARCHAR(150),
        @HomeAddress NVARCHAR(255),
        @PassportData NVARCHAR(100),
        @TariffName NVARCHAR(100),
        @TariffPlanID INT,
        @SubscriberID INT,
        @StaffID INT = 1,  -- ���� ���������
        @TariffPlanCount INT = 500,
        @SubscriberCount INT = 500,
        @ContractCount INT = 2000,
        @PhoneNumber NVARCHAR(20),
        @ContractDate DATE,
        @ContractEndDate DATE,
        @MinSymbols INT,
        @MaxSymbols INT;

-- ���������� ��������� ��� ����������
INSERT INTO StaffPosition (PositionName)
VALUES ('��������');

-- ���������� ����������
INSERT INTO Staff (FullName, PositionID, Education)
VALUES ('���� ������', 1, '������');

-- ���������� ������� �������� ������
SET @i = 1;
SET @MinSymbols = 5;
SET @MaxSymbols = 15;

WHILE @i <= @TariffPlanCount
BEGIN
    -- ��������� ���������� �������� ��������� �����
    SET @NameLimit = @MinSymbols + RAND() * (@MaxSymbols - @MinSymbols);
    SET @TariffName = '';
    WHILE LEN(@TariffName) < @NameLimit
    BEGIN
        SET @Position = RAND() * 52;
        SET @TariffName = @TariffName + SUBSTRING(@Symbol, @Position, 1);
    END;
    
    -- ������� ������ � ������� �������
    INSERT INTO TariffPlans (TariffName, SubscriptionFee, LocalCallRate, LongDistanceCallRate, InternationalCallRate, IsPerSecond, SmsRate, MmsRate, DataRatePerMB)
    VALUES (@TariffName, RAND() * 100, RAND() * 2, RAND() * 5, RAND() * 10, CAST(ROUND(RAND(), 0) AS BIT), RAND() * 2, RAND() * 5, RAND() * 10);
    
    SET @i = @i + 1;
END;

-- ���������� ������� ���������
SET @i = 1;
SET @MinSymbols = 5;
SET @MaxSymbols = 15;

WHILE @i <= @SubscriberCount
BEGIN
    -- ��������� ��������� ������ ��������
    SET @NameLimit = @MinSymbols + RAND() * (@MaxSymbols - @MinSymbols);
    SET @FullName = '';
    SET @HomeAddress = '';
    SET @PassportData = '';
    
    -- ��������� ���
    WHILE LEN(@FullName) < @NameLimit
    BEGIN
        SET @Position = RAND() * 52;
        SET @FullName = @FullName + SUBSTRING(@Symbol, @Position, 1);
    END;

    -- ��������� ��������� ������
    WHILE LEN(@HomeAddress) < @NameLimit + 5
    BEGIN
        SET @Position = RAND() * 52;
        SET @HomeAddress = @HomeAddress + SUBSTRING(@Symbol, @Position, 1);
    END;

    -- ��������� ���������� ������
    WHILE LEN(@PassportData) < 10
    BEGIN
        SET @Position = RAND() * 52;
        SET @PassportData = @PassportData + SUBSTRING(@Symbol, @Position, 1);
    END;

    -- ������� ������ � ������� ���������
    INSERT INTO Subscribers (FullName, HomeAddress, PassportData)
    VALUES (@FullName, @HomeAddress, @PassportData);

    SET @i = @i + 1;
END;

-- ���������� ������� ���������
SET @i = 1;

WHILE @i <= @ContractCount
BEGIN
    -- ����� ���������� �������� � ��������� �����
    SET @SubscriberID = CAST(1 + RAND() * (@SubscriberCount - 1) AS INT);
    SET @TariffPlanID = CAST(1 + RAND() * (@TariffPlanCount - 1) AS INT);
    
    -- ��������� ����������� ������ ��������
    SET @PhoneNumber = '89' + CAST(CAST(RAND() * 1000000000 AS INT) AS NVARCHAR(10));
    
    -- ��������� ��� ���������
    SET @ContractDate = DATEADD(DAY, -RAND() * 3650, GETDATE());  -- ���� ������
    SET @ContractEndDate = DATEADD(YEAR, 1 + RAND() * 5, @ContractDate); -- ���� ���������

    -- �������� �� ���������� ���������� ��� ��������
    IF NOT EXISTS (
        SELECT 1 
        FROM Contracts 
        WHERE SubscriberID = @SubscriberID 
          AND @ContractDate BETWEEN ContractDate AND ISNULL(ContractEndDate, GETDATE())
    )
    BEGIN
        -- ������� ������ � ������� ���������
        INSERT INTO Contracts (SubscriberID, TariffPlanID, ContractDate, ContractEndDate, PhoneNumber, StaffID)
        VALUES (@SubscriberID, @TariffPlanID, @ContractDate, @ContractEndDate, @PhoneNumber, @StaffID);
        
        SET @i = @i + 1;
    END;
END;


-- ���������� ������� �������
DECLARE @CallCount INT = 10000; -- ���������� ������� � �������
DECLARE @CallDate DATETIME;
DECLARE @CallDuration INT;

SET @i = 1;
WHILE @i <= @CallCount
BEGIN
    -- ����� ���������� ���������
    DECLARE @ContractID INT = CAST(1 + RAND() * (SELECT COUNT(*) FROM Contracts) AS INT);
    
    -- ��������� ���� ������ � ��������� ���������
    SELECT @ContractDate = ContractDate, @ContractEndDate = ContractEndDate
    FROM Contracts
    WHERE ContractID = @ContractID;

    -- ��������� ��������� ���� ������ � �������� �������� ���������
    SET @CallDate = DATEADD(DAY, CAST(RAND() * DATEDIFF(DAY, @ContractDate, ISNULL(@ContractEndDate, GETDATE())) AS INT), @ContractDate);
    
    -- ��������� ��������� ������������ ������ (� ��������)
    SET @CallDuration = CAST(1 + RAND() * 3600 AS INT); -- �� 1 ����

    -- ������� ������ � ������� �������
    INSERT INTO Calls (ContractID, CallDate, CallDuration)
    VALUES (@ContractID, @CallDate, @CallDuration);

    SET @i = @i + 1;
END;

-- ���������� ������� ���������
DECLARE @MessageCount INT = 5000; -- ���������� ������� � ����������
DECLARE @MessageDate DATETIME;
DECLARE @IsMMS BIT;

SET @i = 1;
WHILE @i <= @MessageCount
BEGIN
    -- ����� ���������� ���������
    SET @ContractID = CAST(1 + RAND() * (SELECT COUNT(*) FROM Contracts) AS INT);
    
    -- ��������� ���� ������ � ��������� ���������
    SELECT @ContractDate = ContractDate, @ContractEndDate = ContractEndDate
    FROM Contracts
    WHERE ContractID = @ContractID;

    -- ��������� ��������� ���� ��������� � �������� �������� ���������
    SET @MessageDate = DATEADD(DAY, CAST(RAND() * DATEDIFF(DAY, @ContractDate, ISNULL(@ContractEndDate, GETDATE())) AS INT), @ContractDate);
    
    -- ��������� ���� ��������� (SMS ��� MMS)
    SET @IsMMS = CAST(ROUND(RAND(), 0) AS BIT);

    -- ������� ������ � ������� ���������
    INSERT INTO Messages (ContractID, MessageDate, IsMMS)
    VALUES (@ContractID, @MessageDate, @IsMMS);

    SET @i = @i + 1;
END;

-- ���������� ������� ������������� ���������
DECLARE @UsageCount INT = 10000; -- ���������� ������� � �������
DECLARE @UsageDate DATETIME;
DECLARE @DataSentMB DECIMAL(10, 2);
DECLARE @DataReceivedMB DECIMAL(10, 2);

SET @i = 1;
WHILE @i <= @UsageCount
BEGIN
    -- ����� ���������� ���������
    SET @ContractID = CAST(1 + RAND() * (SELECT COUNT(*) FROM Contracts) AS INT);
    
    -- ��������� ���� ������ � ��������� ���������
    SELECT @ContractDate = ContractDate, @ContractEndDate = ContractEndDate
    FROM Contracts
    WHERE ContractID = @ContractID;

    -- ��������� ��������� ���� ������������� ��������� � �������� �������� ���������
    SET @UsageDate = DATEADD(DAY, CAST(RAND() * DATEDIFF(DAY, @ContractDate, ISNULL(@ContractEndDate, GETDATE())) AS INT), @ContractDate);
    
    -- ��������� ���������� ������ ������
    SET @DataSentMB = ROUND(RAND() * 500, 2); -- �� 500 MB
    SET @DataReceivedMB = ROUND(RAND() * 500, 2); -- �� 500 MB

    -- ������� ������ � ������� ������������� ���������
    INSERT INTO InternetUsage (ContractID, UsageDate, DataSentMB, DataReceivedMB)
    VALUES (@ContractID, @UsageDate, @DataSentMB, @DataReceivedMB);

    SET @i = @i + 1;
END;


-- ���������� ������� � ������� StaffPosition
SELECT 'StaffPosition' AS TableName, COUNT(*) AS RecordCount
FROM StaffPosition;

-- ���������� ������� � ������� Staff
SELECT 'Staff' AS TableName, COUNT(*) AS RecordCount
FROM Staff;

-- ���������� ������� � ������� TariffPlans
SELECT 'TariffPlans' AS TableName, COUNT(*) AS RecordCount
FROM TariffPlans;

-- ���������� ������� � ������� Subscribers
SELECT 'Subscribers' AS TableName, COUNT(*) AS RecordCount
FROM Subscribers;

-- ���������� ������� � ������� Contracts
SELECT 'Contracts' AS TableName, COUNT(*) AS RecordCount
FROM Contracts;

-- ���������� ������� � ������� Calls
SELECT 'Calls' AS TableName, COUNT(*) AS RecordCount
FROM Calls;

-- ���������� ������� � ������� Messages
SELECT 'Messages' AS TableName, COUNT(*) AS RecordCount
FROM Messages;

-- ���������� ������� � ������� InternetUsage
SELECT 'InternetUsage' AS TableName, COUNT(*) AS RecordCount
FROM InternetUsage;
