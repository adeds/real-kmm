package id.adeds.shared.data_cache.sqldelight

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import id.adeds.shared.utils.Constant.Companion.DB_NAME
import id.id.adeds.shared.data_cache.sqldelight.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, DB_NAME)
    }
}