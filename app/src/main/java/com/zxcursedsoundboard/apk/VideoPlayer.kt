package com.zxcursedsoundboard.apk

//
//@Destination
//@Composable
//fun VideoPlayerScreen(navigator: DestinationsNavigator) {
//    val context = LocalContext.current
//    AndroidView(
//        factory = {
//            VideoView(it, null).apply {
//                setVideoURI(Uri.parse("android.resource://${context.packageName}/${R.raw.videoplayback}"))
//                start()
//                setOnCompletionListener {
//                    navigator.navigate(FirstDestination)
//                }
//            }
//        },
//        modifier = Modifier.fillMaxSize()
//    )
////
////    val scope = rememberCoroutineScope()
////    val scaffoldState = rememberScaffoldState()
////
////    Box(modifier = Modifier.fillMaxSize()) {
////        var showSnackbar by remember {
////            mutableStateOf(true)
////        }
////        if (showSnackbar) {
////          Snackbar(modifier = Modifier.align(Alignment.Center),
////                action = {
////                    Text(text = "Action",
////                        color = Color(0xffCE93D8),
////                        modifier = Modifier.clickable {
////                            showSnackbar = false
////                        }
////                    )
////                }
////          ) {
////                Text("Message", textAlign = TextAlign.Center,color = Color.White)
////            }
////        }
////    }
////
////
////    val context = LocalContext.current
////    var playWhenReady by remember { mutableStateOf(true) }
////    val exoPlayer = remember {
////        ExoPlayer.Builder(context).build().apply {
////            setMediaItem(MediaItem.fromUri("https://getfile.dokpub.com/yandex/get/https://disk.yandex.ru/i/J2HFVonfmbbwAA"))
////            playWhenReady = !playWhenReady
////            prepare()
////            play()
////        }
////    }
////    DisposableEffect(key1 = exoPlayer) {
////        onDispose {
////            exoPlayer.release()
////        }
////    }
////
////    AndroidView(modifier = Modifier
////        .fillMaxSize(), factory = {
////        StyledPlayerView(context).apply {
////            player = exoPlayer
////            useController = false
////            layoutParams = FrameLayout.LayoutParams(
////                ViewGroup.LayoutParams.WRAP_CONTENT,
////                ViewGroup.LayoutParams.WRAP_CONTENT
////            )
////        }
////      }
////    )
//
//}