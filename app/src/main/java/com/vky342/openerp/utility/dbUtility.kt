package com.vky342.openerp.utility

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.room.Room
import com.vky342.openerp.data.Modules.OpenERPDataBase
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream
import kotlin.system.exitProcess

fun backupDatabaseToUri(context: Context, uri: Uri): Boolean {
    return try {
        val dbDir = context.getDatabasePath("OpenERPDB").parentFile
        val dbBaseName = "OpenERPDB"
        val filesToBackup = listOf(
            File(dbDir, dbBaseName),
            File(dbDir, "$dbBaseName-shm"),
            File(dbDir, "$dbBaseName-wal")
        )

        context.contentResolver.openOutputStream(uri)?.use { outputStream ->
            ZipOutputStream(BufferedOutputStream(outputStream)).use { zipOut ->
                for (file in filesToBackup) {
                    if (file.exists()) {
                        FileInputStream(file).use { input ->
                            val entry = ZipEntry(file.name)
                            zipOut.putNextEntry(entry)
                            input.copyTo(zipOut)
                            zipOut.closeEntry()
                        }
                    }
                }
            }
        }

        Log.d("BACKUP", "Backup complete")
        true
    } catch (e: Exception) {
        Log.e("BACKUP", "Backup failed", e)
        false
    }
}


fun restoreDatabaseFromUri(context: Context, uri: Uri): Boolean {
    return try {
        val dbDir = context.getDatabasePath("OpenERPDB").parentFile!!
        val dbBaseName = "OpenERPDB"

        // Close the DB if possible
        Room.databaseBuilder(context, OpenERPDataBase::class.java, dbBaseName).build().close()

        // Delete existing DB files
        listOf(
            File(dbDir, dbBaseName),
            File(dbDir, "$dbBaseName-shm"),
            File(dbDir, "$dbBaseName-wal")
        ).forEach { if (it.exists()) it.delete() }

        // Extract from ZIP
        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            ZipInputStream(BufferedInputStream(inputStream)).use { zipIn ->
                var entry: ZipEntry? = zipIn.nextEntry
                while (entry != null) {
                    val outFile = File(dbDir, entry.name)
                    FileOutputStream(outFile).use { output ->
                        zipIn.copyTo(output)
                    }
                    zipIn.closeEntry()
                    entry = zipIn.nextEntry
                }
            }
        }

        // Restart app to reload Room
        restartApp(context)
        true
    } catch (e: Exception) {
        Log.e("RESTORE", "Restore failed", e)
        false
    }
}

fun restartApp(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    exitProcess(0) // Kills the current process
}
