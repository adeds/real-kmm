package id.adeds.shared.data.cache

import com.squareup.sqldelight.db.SqlDriver
import id.adeds.shared.utils.Constant

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, Constant.DB_NAME)
    }
}