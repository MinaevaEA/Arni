package com.arni.remote.exceptions

import java.io.IOException

class ArniRemoteException(val remoteMessage: String) : IOException(remoteMessage) {
}
