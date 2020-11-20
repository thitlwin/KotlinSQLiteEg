package com.twinrock.sqliteeg

class DatabaseSchema {
    interface TableNames {
        companion object {
            const val USER = "users"
        }
    }

    interface CreateTable {
        companion object {
            const val USER =
                "Create Table If Not Exists ${TableNames.USER} " +
                        "(name Text Not Null, " +
                        "phone Text Not Null, " +
                        "address Text Not Null);"

        }
    }
}