const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
const admin = require('firebase-admin');
admin.initializeApp();
exports.Deletedata = functions.database.ref('/Comment/{bar}')
    .onCreate((snapshot, context) => {
    const unid = context.params.bar;
    console.log('Testing stuff', unid);
    const com = snapshot.ref.parent;
    const com1 = snapshot.ref.parent.parent.child('USER');
    com1.once('value').then(function (snap){
    return snap.forEach(function(child) {
        const childVal = child.val(); // <- and here you get the values of these children as JavaScript objects
        console.log('USER',childVal);
        if(childVal.CommentCount==5){
          const uid=childVal.UniqueID;
          console.log('uid',uid);

          return com.once('value').then(function (snap1){
          snap1.forEach(function(child1) {
              const childVal1 = child1.val(); // <- and here you get the values of these children as JavaScript objects
              console.log('Comment1',childVal1);
              if(childVal1.UniqueID==uid){
                return child1.ref.set(null)
              }
          });
      });
        }

    });
});
});
