from glob import glob
import os
pre = "LC_"
#[os.rename(f, f[2:]) for f in glob("*.java")]
[os.rename(f, "{}{}".format(pre, f)) for f in glob("*.java")]
#for f in glob("*.java"):
#    print(f)
