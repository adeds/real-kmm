package id.adeds.shared.data_cache.sqldelight

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import id.adeds.shared.utils.Constant.Companion.DB_NAME
import id.id.adeds.shared.data_cache.sqldelight.AppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, DB_NAME)
    }
}