package com.zxcursedsoundboard.apk.core.presentation
//
//class MyMediaSessionService : MediaBrowserServiceCompat() {
//    private lateinit var mediaSession: MediaSessionCompat
//
//    override fun onCreate() {
//        super.onCreate()
//
//        // Create a MediaSessionCompat object
//        mediaSession = MediaSessionCompat(this, "MyMediaSessionService")
//        // Set a callback for the MediaSessionCompat object
//        mediaSession.setCallback(MediaSessionCallback())
//        // Set an active state for the MediaSessionCompat object
//        mediaSession.isActive = true
//    }
//
//    override fun onDestroy() {
//        // Release the MediaSessionCompat object when the Service is destroyed
//        mediaSession.isActive = false
//        mediaSession.release()
//        super.onDestroy()
//    }
//
//    override fun onGetRoot(
//        clientPackageName: String,
//        clientUid: Int,
//        rootHints: Bundle?
//    ): BrowserRoot? {
//        // Return a root ID for the MediaBrowserServiceCompat object
//        return BrowserRoot("root", null)
//    }
//
//    override fun onLoadChildren(
//        parentId: String,
//        result: Result<List<MediaBrowserCompat.MediaItem>>
//    ) {
//        // Load media items for the MediaBrowserServiceCompat object
//        result.sendResult(listOf(/* your media items here */))
//    }
//
//    private inner class MediaSessionCallback : MediaSessionCompat.Callback() {
//        override fun onMediaButtonEvent(mediaButtonEvent: Intent?): Boolean {
//            // Handle media button events here
//            return super.onMediaButtonEvent(mediaButtonEvent)
//        }
//    }
//}
