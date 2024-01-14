package com.arni.remote.extentions

import java.io.IOException

class ArniRemoteExtentions (val remoteMessage: String) : IOException(remoteMessage)
