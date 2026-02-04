@Database(
    entities = [StudentEntity::class, AttendanceEntity::class, UserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "attendance_db"
                ).allowMainThreadQueries()
                 .build()
                 .also { INSTANCE = it }
            }
        }
    }
}