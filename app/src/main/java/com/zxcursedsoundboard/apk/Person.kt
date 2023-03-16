package com.zxcursedsoundboard.apk

//
//@Destination
//@Composable
//fun Person(
//    navigator: DestinationsNavigator,
//    color1: Color,
//    color2: Color,
//    color3: Color, color4: Color, scope: CoroutineScope, scaffoldState: ScaffoldState,
//) {
//    val context = LocalContext.current
//
//    Row(
//        modifier = Modifier
//            .padding(5.dp)
//    ) {
//        val imageLoader = ImageLoader.Builder(context)
//            .components {
//                if (Build.VERSION.SDK_INT >= 28) {
//                    add(ImageDecoderDecoder.Factory())
//                } else {
//                    add(GifDecoder.Factory())
//                }
//            }
//            .build()
//        Spacer(modifier = Modifier.width(5.dp))
//
//        Image(
//            painter = painterResource(id = R.drawable.photo8),
//            contentDescription = null,
//            modifier = Modifier
//                .clip(CircleShape)
//                .weight(1f)
//                .aspectRatio(1f)
//                .border(1.dp, color1, CircleShape)
//                .clickable(onClick = { navigator.navigate(FirstDestination) }),
//            contentScale = ContentScale.Crop,
//
//            )
//        Spacer(modifier = Modifier.width(5.dp))
//
//        Image(
//            painter = rememberAsyncImagePainter(model = R.drawable.zxc2,
//                imageLoader = imageLoader),
//            contentDescription = null,
//            modifier = Modifier
//                .clip(CircleShape)
//                .weight(1f)
//                .aspectRatio(1f)
//                .border(1.dp, color2, CircleShape)
//                .clickable(onClick = {
////                    list = listOf(
////                        context.getString(R.string.pivo),
////                        context.getString(R.string.molchat),
////                        context.getString(R.string.traxat),
////                        context.getString(R.string.pikaper),
////                        context.getString(R.string.sydalut),
////                        context.getString(R.string.madmyazel),
////                        context.getString(R.string.chtoetoblyat),
////                        context.getString(R.string.spasibo),
////                        context.getString(R.string.denegnet),
////                        context.getString(R.string.minuspivo),
////                        context.getString(R.string.anekdot),
////                        context.getString(R.string.dirkavsire),
////                    )
//                    // navigator.navigate(SecondDestination)
//                }),
//            contentScale = ContentScale.Crop,
//        )
//        Spacer(modifier = Modifier.width(5.dp))
//        Image(
//            painter = painterResource(id = R.drawable.photo12),
//            contentDescription = null,
//            modifier = Modifier
//                .clip(CircleShape)
//                .weight(1f)
//                .aspectRatio(1f)
//                .border(1.dp, color3, CircleShape),
//            contentScale = ContentScale.Crop
//
//        )
//        Spacer(modifier = Modifier.width(5.dp))
//        Image(
//            painter = rememberAsyncImagePainter(model = R.drawable.zxc,
//                imageLoader = imageLoader),
//            contentDescription = null,
//            modifier = Modifier
//                .clip(CircleShape)
//                .weight(1f)
//                .aspectRatio(1f)
//                .border(1.dp, color4, CircleShape),
//            contentScale = ContentScale.Crop,
//        )
//        Spacer(modifier = Modifier.width(5.dp))
//        Image(
//            painter = painterResource(id = R.drawable.photo22),
//            contentDescription = null,
//            modifier = Modifier
//                .clip(CircleShape)
//                .weight(1f)
//                .aspectRatio(1f)
//                .border(1.dp, Color.White, CircleShape)
//                .background(Color.Black)
//                .padding(10.dp)
//                .clickable {
//                    scope.launch {
//                        scaffoldState.drawerState.open()
//                    }
//                },
//            contentScale = ContentScale.Crop,
//        )
//        Spacer(modifier = Modifier.width(5.dp))
//
//    }
//    Spacer(modifier = Modifier.height(5.dp))
//
//
//}
