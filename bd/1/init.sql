-- Таблица для хранения тарифных планов
CREATE TABLE TariffPlans (
    TariffPlanID INT PRIMARY KEY IDENTITY(1,1),  -- Идентификатор тарифного плана
    TariffName NVARCHAR(100) NOT NULL,           -- Название тарифного плана
    SubscriptionFee DECIMAL(10, 2) NOT NULL,     -- Абонентская плата
    LocalCallRate DECIMAL(10, 2) NOT NULL,       -- Стоимость минуты разговора при местной связи
    LongDistanceCallRate DECIMAL(10, 2) NOT NULL,-- Стоимость минуты разговора при междугородной связи
    InternationalCallRate DECIMAL(10, 2) NOT NULL,-- Стоимость минуты разговора при международной связи
    IsPerSecond BIT NOT NULL,                    -- Если FALSE, то тарификация поминутная; если TRUE, то посекундная
    SmsRate DECIMAL(10, 2) NOT NULL,             -- Стоимость SMS
    MmsRate DECIMAL(10, 2) NOT NULL,             -- Стоимость MMS
    DataRatePerMB DECIMAL(10, 2) NOT NULL        -- Стоимость передачи 1 МБ данных
);

-- Таблица для хранения информации об абонентах
CREATE TABLE Subscribers (
    SubscriberID INT PRIMARY KEY IDENTITY(1,1),  -- Идентификатор абонента
    FullName NVARCHAR(150) NOT NULL,             -- ФИО абонента
    HomeAddress NVARCHAR(255),                   -- Домашний адрес абонента
    PassportData NVARCHAR(100)                   -- Паспортные данные абонента
);

-- Таблица для хранения договоров на оказание услуг
CREATE TABLE Contracts (
    ContractID INT PRIMARY KEY IDENTITY(1,1),    -- Идентификатор договора
    SubscriberID INT NOT NULL FOREIGN KEY REFERENCES Subscribers(SubscriberID),  -- Идентификатор абонента
    TariffPlanID INT NOT NULL FOREIGN KEY REFERENCES TariffPlans(TariffPlanID),  -- Идентификатор тарифного плана
    ContractDate DATE NOT NULL,                  -- Дата заключения договора
    ContractEndDate DATE NULL,                   -- Дата окончания договора (может быть NULL)
    PhoneNumber NVARCHAR(20) NOT NULL,           -- Телефонный номер, присвоенный абоненту
    StaffID INT NOT NULL FOREIGN KEY REFERENCES Staff(StaffID)  -- Ссылка на сотрудника, оформившего договор
);

-- Таблица для хранения информации о звонках
CREATE TABLE Calls (
    CallID INT PRIMARY KEY IDENTITY(1,1),        -- Идентификатор звонка
    ContractID INT NOT NULL FOREIGN KEY REFERENCES Contracts(ContractID), -- Ссылка на договор
    CallDate DATETIME NOT NULL,                  -- Дата и время звонка
    CallDuration INT NOT NULL                    -- Продолжительность разговора (в секундах)
);

-- Таблица для хранения информации о смс и ммс
CREATE TABLE Messages (
    MessageID INT PRIMARY KEY IDENTITY(1,1),     -- Идентификатор сообщения
    ContractID INT NOT NULL FOREIGN KEY REFERENCES Contracts(ContractID), -- Ссылка на договор
    MessageDate DATETIME NOT NULL,               -- Дата и время отправки сообщения (SMS или MMS)
    IsMMS BIT NOT NULL                           -- Если значение FALSE, то это SMS; если TRUE, то MMS
);

-- Таблица для хранения информации о трафике интернета
CREATE TABLE InternetUsage (
    UsageID INT PRIMARY KEY IDENTITY(1,1),       -- Идентификатор записи использования интернета
    ContractID INT NOT NULL FOREIGN KEY REFERENCES Contracts(ContractID), -- Ссылка на договор
    UsageDate DATETIME NOT NULL,                 -- Дата и время использования интернета
    DataSentMB DECIMAL(10, 2) NOT NULL,          -- Объем отправленных данных (в МБ)
    DataReceivedMB DECIMAL(10, 2) NOT NULL       -- Объем полученных данных (в МБ)
);

-- Отдел кадров (данные обо всех сотрудниках)
CREATE TABLE Staff (
    StaffID INT PRIMARY KEY IDENTITY(1,1),       -- Идентификатор сотрудника
    FullName NVARCHAR(150) NOT NULL,             -- ФИО сотрудника
    PositionID INT NOT NULL FOREIGN KEY REFERENCES StaffPosition(PositionID), -- Идентификатор должности
    Education NVARCHAR(100)                      -- Образование сотрудника
);

-- Таблица для хранения должностей сотрудников
CREATE TABLE StaffPosition (
    PositionID INT PRIMARY KEY IDENTITY(1,1),    -- Идентификатор должности
    PositionName NVARCHAR(100) NOT NULL          -- Название должности (например, "Менеджер", "Оператор")
);
