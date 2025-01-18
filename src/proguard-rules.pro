# Add any ProGuard configurations specific to this
# extension here.

-keep public class ph.bxtdev.Rapu.Rapu {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'ph/bxtdev/Rapu/repack'
-flattenpackagehierarchy
-dontpreverify
