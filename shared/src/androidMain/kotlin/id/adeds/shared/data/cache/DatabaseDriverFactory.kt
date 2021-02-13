package id.adeds.shared.data.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import id.adeds.shared.utils.Constant
import id.adeds.shared.data_cache.sqldelight.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, Constant.DB_NAME)
    }
}