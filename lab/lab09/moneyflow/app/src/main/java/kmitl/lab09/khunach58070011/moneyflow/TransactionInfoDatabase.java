package kmitl.lab09.khunach58070011.moneyflow;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Transaction;

@Database(entities = {TransactionInfo.class}, version = 1)
public abstract class TransactionInfoDatabase extends RoomDatabase{
    public abstract TransactionDAO transactionDAO();
}
