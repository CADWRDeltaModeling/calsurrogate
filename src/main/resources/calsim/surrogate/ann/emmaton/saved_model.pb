хц
ф»
D
AddV2
x"T
y"T
z"T"
Ttype:
2	АР
B
AssignVariableOp
resource
value"dtype"
dtypetypeИ
~
BiasAdd

value"T	
bias"T
output"T" 
Ttype:
2	"-
data_formatstringNHWC:
NHWCNCHW
h
ConcatV2
values"T*N
axis"Tidx
output"T"
Nint(0"	
Ttype"
Tidxtype0:
2	
8
Const
output"dtype"
valuetensor"
dtypetype
.
Identity

input"T
output"T"	
Ttype
q
MatMul
a"T
b"T
product"T"
transpose_abool( "
transpose_bbool( "
Ttype:

2	
>
Maximum
x"T
y"T
z"T"
Ttype:
2	
e
MergeV2Checkpoints
checkpoint_prefixes
destination_prefix"
delete_old_dirsbool(И
?
Mul
x"T
y"T
z"T"
Ttype:
2	Р

NoOp
M
Pack
values"T*N
output"T"
Nint(0"	
Ttype"
axisint 
C
Placeholder
output"dtype"
dtypetype"
shapeshape:
@
ReadVariableOp
resource
value"dtype"
dtypetypeИ
@
RealDiv
x"T
y"T
z"T"
Ttype:
2	
E
Relu
features"T
activations"T"
Ttype:
2	
o
	RestoreV2

prefix
tensor_names
shape_and_slices
tensors2dtypes"
dtypes
list(type)(0И
.
Rsqrt
x"T
y"T"
Ttype:

2
l
SaveV2

prefix
tensor_names
shape_and_slices
tensors2dtypes"
dtypes
list(type)(0И
?
Select
	condition

t"T
e"T
output"T"	
Ttype
H
ShardedFilename
basename	
shard

num_shards
filename
0
Sigmoid
x"T
y"T"
Ttype:

2
-
Sqrt
x"T
y"T"
Ttype:

2
Ѕ
StatefulPartitionedCall
args2Tin
output2Tout"
Tin
list(type)("
Tout
list(type)("	
ffunc"
configstring "
config_protostring "
executor_typestring И®
@
StaticRegexFullMatch	
input

output
"
patternstring
N

StringJoin
inputs*N

output"
Nint(0"
	separatorstring 
<
Sub
x"T
y"T
z"T"
Ttype:
2	
Ц
VarHandleOp
resource"
	containerstring "
shared_namestring "
dtypetype"
shapeshape"#
allowed_deviceslist(string)
 И"serve*2.7.42v2.7.3-139-ga73cc22ba398£Т
\
meanVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namemean
U
mean/Read/ReadVariableOpReadVariableOpmean*
_output_shapes
: *
dtype0
d
varianceVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name
variance
]
variance/Read/ReadVariableOpReadVariableOpvariance*
_output_shapes
: *
dtype0
^
countVarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_namecount
W
count/Read/ReadVariableOpReadVariableOpcount*
_output_shapes
: *
dtype0	
`
mean_1VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namemean_1
Y
mean_1/Read/ReadVariableOpReadVariableOpmean_1*
_output_shapes
: *
dtype0
h

variance_1VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name
variance_1
a
variance_1/Read/ReadVariableOpReadVariableOp
variance_1*
_output_shapes
: *
dtype0
b
count_1VarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_name	count_1
[
count_1/Read/ReadVariableOpReadVariableOpcount_1*
_output_shapes
: *
dtype0	
`
mean_2VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namemean_2
Y
mean_2/Read/ReadVariableOpReadVariableOpmean_2*
_output_shapes
: *
dtype0
h

variance_2VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name
variance_2
a
variance_2/Read/ReadVariableOpReadVariableOp
variance_2*
_output_shapes
: *
dtype0
b
count_2VarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_name	count_2
[
count_2/Read/ReadVariableOpReadVariableOpcount_2*
_output_shapes
: *
dtype0	
`
mean_3VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namemean_3
Y
mean_3/Read/ReadVariableOpReadVariableOpmean_3*
_output_shapes
: *
dtype0
h

variance_3VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name
variance_3
a
variance_3/Read/ReadVariableOpReadVariableOp
variance_3*
_output_shapes
: *
dtype0
b
count_3VarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_name	count_3
[
count_3/Read/ReadVariableOpReadVariableOpcount_3*
_output_shapes
: *
dtype0	
`
mean_4VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namemean_4
Y
mean_4/Read/ReadVariableOpReadVariableOpmean_4*
_output_shapes
: *
dtype0
h

variance_4VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name
variance_4
a
variance_4/Read/ReadVariableOpReadVariableOp
variance_4*
_output_shapes
: *
dtype0
b
count_4VarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_name	count_4
[
count_4/Read/ReadVariableOpReadVariableOpcount_4*
_output_shapes
: *
dtype0	
`
mean_5VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namemean_5
Y
mean_5/Read/ReadVariableOpReadVariableOpmean_5*
_output_shapes
: *
dtype0
h

variance_5VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name
variance_5
a
variance_5/Read/ReadVariableOpReadVariableOp
variance_5*
_output_shapes
: *
dtype0
b
count_5VarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_name	count_5
[
count_5/Read/ReadVariableOpReadVariableOpcount_5*
_output_shapes
: *
dtype0	
x
dense_3/kernelVarHandleOp*
_output_shapes
: *
dtype0*
shape
:~*
shared_namedense_3/kernel
q
"dense_3/kernel/Read/ReadVariableOpReadVariableOpdense_3/kernel*
_output_shapes

:~*
dtype0
p
dense_3/biasVarHandleOp*
_output_shapes
: *
dtype0*
shape:*
shared_namedense_3/bias
i
 dense_3/bias/Read/ReadVariableOpReadVariableOpdense_3/bias*
_output_shapes
:*
dtype0
О
batch_normalization_3/gammaVarHandleOp*
_output_shapes
: *
dtype0*
shape:*,
shared_namebatch_normalization_3/gamma
З
/batch_normalization_3/gamma/Read/ReadVariableOpReadVariableOpbatch_normalization_3/gamma*
_output_shapes
:*
dtype0
М
batch_normalization_3/betaVarHandleOp*
_output_shapes
: *
dtype0*
shape:*+
shared_namebatch_normalization_3/beta
Е
.batch_normalization_3/beta/Read/ReadVariableOpReadVariableOpbatch_normalization_3/beta*
_output_shapes
:*
dtype0
Ъ
!batch_normalization_3/moving_meanVarHandleOp*
_output_shapes
: *
dtype0*
shape:*2
shared_name#!batch_normalization_3/moving_mean
У
5batch_normalization_3/moving_mean/Read/ReadVariableOpReadVariableOp!batch_normalization_3/moving_mean*
_output_shapes
:*
dtype0
Ґ
%batch_normalization_3/moving_varianceVarHandleOp*
_output_shapes
: *
dtype0*
shape:*6
shared_name'%batch_normalization_3/moving_variance
Ы
9batch_normalization_3/moving_variance/Read/ReadVariableOpReadVariableOp%batch_normalization_3/moving_variance*
_output_shapes
:*
dtype0
v
hidden/kernelVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*
shared_namehidden/kernel
o
!hidden/kernel/Read/ReadVariableOpReadVariableOphidden/kernel*
_output_shapes

:*
dtype0
n
hidden/biasVarHandleOp*
_output_shapes
: *
dtype0*
shape:*
shared_namehidden/bias
g
hidden/bias/Read/ReadVariableOpReadVariableOphidden/bias*
_output_shapes
:*
dtype0
В
batch_normalize/gammaVarHandleOp*
_output_shapes
: *
dtype0*
shape:*&
shared_namebatch_normalize/gamma
{
)batch_normalize/gamma/Read/ReadVariableOpReadVariableOpbatch_normalize/gamma*
_output_shapes
:*
dtype0
А
batch_normalize/betaVarHandleOp*
_output_shapes
: *
dtype0*
shape:*%
shared_namebatch_normalize/beta
y
(batch_normalize/beta/Read/ReadVariableOpReadVariableOpbatch_normalize/beta*
_output_shapes
:*
dtype0
О
batch_normalize/moving_meanVarHandleOp*
_output_shapes
: *
dtype0*
shape:*,
shared_namebatch_normalize/moving_mean
З
/batch_normalize/moving_mean/Read/ReadVariableOpReadVariableOpbatch_normalize/moving_mean*
_output_shapes
:*
dtype0
Ц
batch_normalize/moving_varianceVarHandleOp*
_output_shapes
: *
dtype0*
shape:*0
shared_name!batch_normalize/moving_variance
П
3batch_normalize/moving_variance/Read/ReadVariableOpReadVariableOpbatch_normalize/moving_variance*
_output_shapes
:*
dtype0
n
	ec/kernelVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*
shared_name	ec/kernel
g
ec/kernel/Read/ReadVariableOpReadVariableOp	ec/kernel*
_output_shapes

:*
dtype0
f
ec/biasVarHandleOp*
_output_shapes
: *
dtype0*
shape:*
shared_name	ec/bias
_
ec/bias/Read/ReadVariableOpReadVariableOpec/bias*
_output_shapes
:*
dtype0
j
Adamax/iterVarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_nameAdamax/iter
c
Adamax/iter/Read/ReadVariableOpReadVariableOpAdamax/iter*
_output_shapes
: *
dtype0	
n
Adamax/beta_1VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_nameAdamax/beta_1
g
!Adamax/beta_1/Read/ReadVariableOpReadVariableOpAdamax/beta_1*
_output_shapes
: *
dtype0
n
Adamax/beta_2VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_nameAdamax/beta_2
g
!Adamax/beta_2/Read/ReadVariableOpReadVariableOpAdamax/beta_2*
_output_shapes
: *
dtype0
l
Adamax/decayVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_nameAdamax/decay
e
 Adamax/decay/Read/ReadVariableOpReadVariableOpAdamax/decay*
_output_shapes
: *
dtype0
|
Adamax/learning_rateVarHandleOp*
_output_shapes
: *
dtype0*
shape: *%
shared_nameAdamax/learning_rate
u
(Adamax/learning_rate/Read/ReadVariableOpReadVariableOpAdamax/learning_rate*
_output_shapes
: *
dtype0
^
totalVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_nametotal
W
total/Read/ReadVariableOpReadVariableOptotal*
_output_shapes
: *
dtype0
b
count_6VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name	count_6
[
count_6/Read/ReadVariableOpReadVariableOpcount_6*
_output_shapes
: *
dtype0
b
total_1VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name	total_1
[
total_1/Read/ReadVariableOpReadVariableOptotal_1*
_output_shapes
: *
dtype0
b
count_7VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name	count_7
[
count_7/Read/ReadVariableOpReadVariableOpcount_7*
_output_shapes
: *
dtype0
К
Adamax/dense_3/kernel/mVarHandleOp*
_output_shapes
: *
dtype0*
shape
:~*(
shared_nameAdamax/dense_3/kernel/m
Г
+Adamax/dense_3/kernel/m/Read/ReadVariableOpReadVariableOpAdamax/dense_3/kernel/m*
_output_shapes

:~*
dtype0
В
Adamax/dense_3/bias/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*&
shared_nameAdamax/dense_3/bias/m
{
)Adamax/dense_3/bias/m/Read/ReadVariableOpReadVariableOpAdamax/dense_3/bias/m*
_output_shapes
:*
dtype0
†
$Adamax/batch_normalization_3/gamma/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*5
shared_name&$Adamax/batch_normalization_3/gamma/m
Щ
8Adamax/batch_normalization_3/gamma/m/Read/ReadVariableOpReadVariableOp$Adamax/batch_normalization_3/gamma/m*
_output_shapes
:*
dtype0
Ю
#Adamax/batch_normalization_3/beta/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*4
shared_name%#Adamax/batch_normalization_3/beta/m
Ч
7Adamax/batch_normalization_3/beta/m/Read/ReadVariableOpReadVariableOp#Adamax/batch_normalization_3/beta/m*
_output_shapes
:*
dtype0
И
Adamax/hidden/kernel/mVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*'
shared_nameAdamax/hidden/kernel/m
Б
*Adamax/hidden/kernel/m/Read/ReadVariableOpReadVariableOpAdamax/hidden/kernel/m*
_output_shapes

:*
dtype0
А
Adamax/hidden/bias/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*%
shared_nameAdamax/hidden/bias/m
y
(Adamax/hidden/bias/m/Read/ReadVariableOpReadVariableOpAdamax/hidden/bias/m*
_output_shapes
:*
dtype0
Ф
Adamax/batch_normalize/gamma/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*/
shared_name Adamax/batch_normalize/gamma/m
Н
2Adamax/batch_normalize/gamma/m/Read/ReadVariableOpReadVariableOpAdamax/batch_normalize/gamma/m*
_output_shapes
:*
dtype0
Т
Adamax/batch_normalize/beta/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*.
shared_nameAdamax/batch_normalize/beta/m
Л
1Adamax/batch_normalize/beta/m/Read/ReadVariableOpReadVariableOpAdamax/batch_normalize/beta/m*
_output_shapes
:*
dtype0
А
Adamax/ec/kernel/mVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*#
shared_nameAdamax/ec/kernel/m
y
&Adamax/ec/kernel/m/Read/ReadVariableOpReadVariableOpAdamax/ec/kernel/m*
_output_shapes

:*
dtype0
x
Adamax/ec/bias/mVarHandleOp*
_output_shapes
: *
dtype0*
shape:*!
shared_nameAdamax/ec/bias/m
q
$Adamax/ec/bias/m/Read/ReadVariableOpReadVariableOpAdamax/ec/bias/m*
_output_shapes
:*
dtype0
К
Adamax/dense_3/kernel/vVarHandleOp*
_output_shapes
: *
dtype0*
shape
:~*(
shared_nameAdamax/dense_3/kernel/v
Г
+Adamax/dense_3/kernel/v/Read/ReadVariableOpReadVariableOpAdamax/dense_3/kernel/v*
_output_shapes

:~*
dtype0
В
Adamax/dense_3/bias/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*&
shared_nameAdamax/dense_3/bias/v
{
)Adamax/dense_3/bias/v/Read/ReadVariableOpReadVariableOpAdamax/dense_3/bias/v*
_output_shapes
:*
dtype0
†
$Adamax/batch_normalization_3/gamma/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*5
shared_name&$Adamax/batch_normalization_3/gamma/v
Щ
8Adamax/batch_normalization_3/gamma/v/Read/ReadVariableOpReadVariableOp$Adamax/batch_normalization_3/gamma/v*
_output_shapes
:*
dtype0
Ю
#Adamax/batch_normalization_3/beta/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*4
shared_name%#Adamax/batch_normalization_3/beta/v
Ч
7Adamax/batch_normalization_3/beta/v/Read/ReadVariableOpReadVariableOp#Adamax/batch_normalization_3/beta/v*
_output_shapes
:*
dtype0
И
Adamax/hidden/kernel/vVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*'
shared_nameAdamax/hidden/kernel/v
Б
*Adamax/hidden/kernel/v/Read/ReadVariableOpReadVariableOpAdamax/hidden/kernel/v*
_output_shapes

:*
dtype0
А
Adamax/hidden/bias/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*%
shared_nameAdamax/hidden/bias/v
y
(Adamax/hidden/bias/v/Read/ReadVariableOpReadVariableOpAdamax/hidden/bias/v*
_output_shapes
:*
dtype0
Ф
Adamax/batch_normalize/gamma/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*/
shared_name Adamax/batch_normalize/gamma/v
Н
2Adamax/batch_normalize/gamma/v/Read/ReadVariableOpReadVariableOpAdamax/batch_normalize/gamma/v*
_output_shapes
:*
dtype0
Т
Adamax/batch_normalize/beta/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*.
shared_nameAdamax/batch_normalize/beta/v
Л
1Adamax/batch_normalize/beta/v/Read/ReadVariableOpReadVariableOpAdamax/batch_normalize/beta/v*
_output_shapes
:*
dtype0
А
Adamax/ec/kernel/vVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*#
shared_nameAdamax/ec/kernel/v
y
&Adamax/ec/kernel/v/Read/ReadVariableOpReadVariableOpAdamax/ec/kernel/v*
_output_shapes

:*
dtype0
x
Adamax/ec/bias/vVarHandleOp*
_output_shapes
: *
dtype0*
shape:*!
shared_nameAdamax/ec/bias/v
q
$Adamax/ec/bias/v/Read/ReadVariableOpReadVariableOpAdamax/ec/bias/v*
_output_shapes
:*
dtype0
Z
ConstConst*
_output_shapes

:*
dtype0*
valueB*Лќ÷E
\
Const_1Const*
_output_shapes

:*
dtype0*
valueB*$–-K
\
Const_2Const*
_output_shapes

:*
dtype0*
valueB*ќTр>
\
Const_3Const*
_output_shapes

:*
dtype0*
valueB* ∞o>
\
Const_4Const*
_output_shapes

:*
dtype0*
valueB*[ж•D
\
Const_5Const*
_output_shapes

:*
dtype0*
valueB*LщЕI
\
Const_6Const*
_output_shapes

:*
dtype0*
valueB*]B9E
\
Const_7Const*
_output_shapes

:*
dtype0*
valueB*±_K
\
Const_8Const*
_output_shapes

:*
dtype0*
valueB*rОЄ@
\
Const_9Const*
_output_shapes

:*
dtype0*
valueB*\<L?
]
Const_10Const*
_output_shapes

:*
dtype0*
valueB*оПD?
]
Const_11Const*
_output_shapes

:*
dtype0*
valueB*Е1>

NoOpNoOp
Ѓ\
Const_12Const"/device:CPU:0*
_output_shapes
: *
dtype0*ж[
value№[Bў[ B“[
Ю
layer-0
layer-1
layer-2
layer-3
layer-4
layer-5
layer-6
layer-7
	layer_with_weights-0
	layer-8

layer_with_weights-1

layer-9
layer_with_weights-2
layer-10
layer_with_weights-3
layer-11
layer_with_weights-4
layer-12
layer_with_weights-5
layer-13
layer-14
layer_with_weights-6
layer-15
layer_with_weights-7
layer-16
layer_with_weights-8
layer-17
layer_with_weights-9
layer-18
layer_with_weights-10
layer-19
	optimizer
	variables
trainable_variables
regularization_losses
	keras_api

signatures
 
 
 
 
 
 
 
R
	variables
trainable_variables
regularization_losses
	keras_api
•

_keep_axis
 _reduce_axis
!_reduce_axis_mask
"_broadcast_shape
#mean
#
adapt_mean
$variance
$adapt_variance
	%count
&	keras_api
•
'
_keep_axis
(_reduce_axis
)_reduce_axis_mask
*_broadcast_shape
+mean
+
adapt_mean
,variance
,adapt_variance
	-count
.	keras_api
•
/
_keep_axis
0_reduce_axis
1_reduce_axis_mask
2_broadcast_shape
3mean
3
adapt_mean
4variance
4adapt_variance
	5count
6	keras_api
•
7
_keep_axis
8_reduce_axis
9_reduce_axis_mask
:_broadcast_shape
;mean
;
adapt_mean
<variance
<adapt_variance
	=count
>	keras_api
•
?
_keep_axis
@_reduce_axis
A_reduce_axis_mask
B_broadcast_shape
Cmean
C
adapt_mean
Dvariance
Dadapt_variance
	Ecount
F	keras_api
•
G
_keep_axis
H_reduce_axis
I_reduce_axis_mask
J_broadcast_shape
Kmean
K
adapt_mean
Lvariance
Ladapt_variance
	Mcount
N	keras_api
R
O	variables
Ptrainable_variables
Qregularization_losses
R	keras_api
h

Skernel
Tbias
U	variables
Vtrainable_variables
Wregularization_losses
X	keras_api
Ч
Yaxis
	Zgamma
[beta
\moving_mean
]moving_variance
^	variables
_trainable_variables
`regularization_losses
a	keras_api
h

bkernel
cbias
d	variables
etrainable_variables
fregularization_losses
g	keras_api
Ч
haxis
	igamma
jbeta
kmoving_mean
lmoving_variance
m	variables
ntrainable_variables
oregularization_losses
p	keras_api
h

qkernel
rbias
s	variables
ttrainable_variables
uregularization_losses
v	keras_api
И
witer

xbeta_1

ybeta_2
	zdecay
{learning_rateSmѓTm∞Zm±[m≤bm≥cmіimµjmґqmЈrmЄSvєTvЇZvї[vЉbvљcvЊivњjvјqvЅrv¬
ц
#0
$1
%2
+3
,4
-5
36
47
58
;9
<10
=11
C12
D13
E14
K15
L16
M17
S18
T19
Z20
[21
\22
]23
b24
c25
i26
j27
k28
l29
q30
r31
F
S0
T1
Z2
[3
b4
c5
i6
j7
q8
r9
 
Ѓ
|non_trainable_variables

}layers
~metrics
layer_regularization_losses
Аlayer_metrics
	variables
trainable_variables
regularization_losses
 
 
 
 
≤
Бnon_trainable_variables
Вlayers
Гmetrics
 Дlayer_regularization_losses
Еlayer_metrics
	variables
trainable_variables
regularization_losses
 
 
 
 
NL
VARIABLE_VALUEmean4layer_with_weights-0/mean/.ATTRIBUTES/VARIABLE_VALUE
VT
VARIABLE_VALUEvariance8layer_with_weights-0/variance/.ATTRIBUTES/VARIABLE_VALUE
PN
VARIABLE_VALUEcount5layer_with_weights-0/count/.ATTRIBUTES/VARIABLE_VALUE
 
 
 
 
 
PN
VARIABLE_VALUEmean_14layer_with_weights-1/mean/.ATTRIBUTES/VARIABLE_VALUE
XV
VARIABLE_VALUE
variance_18layer_with_weights-1/variance/.ATTRIBUTES/VARIABLE_VALUE
RP
VARIABLE_VALUEcount_15layer_with_weights-1/count/.ATTRIBUTES/VARIABLE_VALUE
 
 
 
 
 
PN
VARIABLE_VALUEmean_24layer_with_weights-2/mean/.ATTRIBUTES/VARIABLE_VALUE
XV
VARIABLE_VALUE
variance_28layer_with_weights-2/variance/.ATTRIBUTES/VARIABLE_VALUE
RP
VARIABLE_VALUEcount_25layer_with_weights-2/count/.ATTRIBUTES/VARIABLE_VALUE
 
 
 
 
 
PN
VARIABLE_VALUEmean_34layer_with_weights-3/mean/.ATTRIBUTES/VARIABLE_VALUE
XV
VARIABLE_VALUE
variance_38layer_with_weights-3/variance/.ATTRIBUTES/VARIABLE_VALUE
RP
VARIABLE_VALUEcount_35layer_with_weights-3/count/.ATTRIBUTES/VARIABLE_VALUE
 
 
 
 
 
PN
VARIABLE_VALUEmean_44layer_with_weights-4/mean/.ATTRIBUTES/VARIABLE_VALUE
XV
VARIABLE_VALUE
variance_48layer_with_weights-4/variance/.ATTRIBUTES/VARIABLE_VALUE
RP
VARIABLE_VALUEcount_45layer_with_weights-4/count/.ATTRIBUTES/VARIABLE_VALUE
 
 
 
 
 
PN
VARIABLE_VALUEmean_54layer_with_weights-5/mean/.ATTRIBUTES/VARIABLE_VALUE
XV
VARIABLE_VALUE
variance_58layer_with_weights-5/variance/.ATTRIBUTES/VARIABLE_VALUE
RP
VARIABLE_VALUEcount_55layer_with_weights-5/count/.ATTRIBUTES/VARIABLE_VALUE
 
 
 
 
≤
Жnon_trainable_variables
Зlayers
Иmetrics
 Йlayer_regularization_losses
Кlayer_metrics
O	variables
Ptrainable_variables
Qregularization_losses
ZX
VARIABLE_VALUEdense_3/kernel6layer_with_weights-6/kernel/.ATTRIBUTES/VARIABLE_VALUE
VT
VARIABLE_VALUEdense_3/bias4layer_with_weights-6/bias/.ATTRIBUTES/VARIABLE_VALUE

S0
T1

S0
T1
 
≤
Лnon_trainable_variables
Мlayers
Нmetrics
 Оlayer_regularization_losses
Пlayer_metrics
U	variables
Vtrainable_variables
Wregularization_losses
 
fd
VARIABLE_VALUEbatch_normalization_3/gamma5layer_with_weights-7/gamma/.ATTRIBUTES/VARIABLE_VALUE
db
VARIABLE_VALUEbatch_normalization_3/beta4layer_with_weights-7/beta/.ATTRIBUTES/VARIABLE_VALUE
rp
VARIABLE_VALUE!batch_normalization_3/moving_mean;layer_with_weights-7/moving_mean/.ATTRIBUTES/VARIABLE_VALUE
zx
VARIABLE_VALUE%batch_normalization_3/moving_variance?layer_with_weights-7/moving_variance/.ATTRIBUTES/VARIABLE_VALUE

Z0
[1
\2
]3

Z0
[1
 
≤
Рnon_trainable_variables
Сlayers
Тmetrics
 Уlayer_regularization_losses
Фlayer_metrics
^	variables
_trainable_variables
`regularization_losses
YW
VARIABLE_VALUEhidden/kernel6layer_with_weights-8/kernel/.ATTRIBUTES/VARIABLE_VALUE
US
VARIABLE_VALUEhidden/bias4layer_with_weights-8/bias/.ATTRIBUTES/VARIABLE_VALUE

b0
c1

b0
c1
 
≤
Хnon_trainable_variables
Цlayers
Чmetrics
 Шlayer_regularization_losses
Щlayer_metrics
d	variables
etrainable_variables
fregularization_losses
 
`^
VARIABLE_VALUEbatch_normalize/gamma5layer_with_weights-9/gamma/.ATTRIBUTES/VARIABLE_VALUE
^\
VARIABLE_VALUEbatch_normalize/beta4layer_with_weights-9/beta/.ATTRIBUTES/VARIABLE_VALUE
lj
VARIABLE_VALUEbatch_normalize/moving_mean;layer_with_weights-9/moving_mean/.ATTRIBUTES/VARIABLE_VALUE
tr
VARIABLE_VALUEbatch_normalize/moving_variance?layer_with_weights-9/moving_variance/.ATTRIBUTES/VARIABLE_VALUE

i0
j1
k2
l3

i0
j1
 
≤
Ъnon_trainable_variables
Ыlayers
Ьmetrics
 Эlayer_regularization_losses
Юlayer_metrics
m	variables
ntrainable_variables
oregularization_losses
VT
VARIABLE_VALUE	ec/kernel7layer_with_weights-10/kernel/.ATTRIBUTES/VARIABLE_VALUE
RP
VARIABLE_VALUEec/bias5layer_with_weights-10/bias/.ATTRIBUTES/VARIABLE_VALUE

q0
r1

q0
r1
 
≤
Яnon_trainable_variables
†layers
°metrics
 Ґlayer_regularization_losses
£layer_metrics
s	variables
ttrainable_variables
uregularization_losses
JH
VARIABLE_VALUEAdamax/iter)optimizer/iter/.ATTRIBUTES/VARIABLE_VALUE
NL
VARIABLE_VALUEAdamax/beta_1+optimizer/beta_1/.ATTRIBUTES/VARIABLE_VALUE
NL
VARIABLE_VALUEAdamax/beta_2+optimizer/beta_2/.ATTRIBUTES/VARIABLE_VALUE
LJ
VARIABLE_VALUEAdamax/decay*optimizer/decay/.ATTRIBUTES/VARIABLE_VALUE
\Z
VARIABLE_VALUEAdamax/learning_rate2optimizer/learning_rate/.ATTRIBUTES/VARIABLE_VALUE
¶
#0
$1
%2
+3
,4
-5
36
47
58
;9
<10
=11
C12
D13
E14
K15
L16
M17
\18
]19
k20
l21
Ц
0
1
2
3
4
5
6
7
	8

9
10
11
12
13
14
15
16
17
18
19

§0
•1
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 

\0
]1
 
 
 
 
 
 
 
 
 

k0
l1
 
 
 
 
 
 
 
 
 
8

¶total

Іcount
®	variables
©	keras_api
I

™total

Ђcount
ђ
_fn_kwargs
≠	variables
Ѓ	keras_api
OM
VARIABLE_VALUEtotal4keras_api/metrics/0/total/.ATTRIBUTES/VARIABLE_VALUE
QO
VARIABLE_VALUEcount_64keras_api/metrics/0/count/.ATTRIBUTES/VARIABLE_VALUE

¶0
І1

®	variables
QO
VARIABLE_VALUEtotal_14keras_api/metrics/1/total/.ATTRIBUTES/VARIABLE_VALUE
QO
VARIABLE_VALUEcount_74keras_api/metrics/1/count/.ATTRIBUTES/VARIABLE_VALUE
 

™0
Ђ1

≠	variables
}
VARIABLE_VALUEAdamax/dense_3/kernel/mRlayer_with_weights-6/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
{y
VARIABLE_VALUEAdamax/dense_3/bias/mPlayer_with_weights-6/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
МЙ
VARIABLE_VALUE$Adamax/batch_normalization_3/gamma/mQlayer_with_weights-7/gamma/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
КЗ
VARIABLE_VALUE#Adamax/batch_normalization_3/beta/mPlayer_with_weights-7/beta/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
~|
VARIABLE_VALUEAdamax/hidden/kernel/mRlayer_with_weights-8/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
zx
VARIABLE_VALUEAdamax/hidden/bias/mPlayer_with_weights-8/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
ЖГ
VARIABLE_VALUEAdamax/batch_normalize/gamma/mQlayer_with_weights-9/gamma/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
ДБ
VARIABLE_VALUEAdamax/batch_normalize/beta/mPlayer_with_weights-9/beta/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
{y
VARIABLE_VALUEAdamax/ec/kernel/mSlayer_with_weights-10/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
wu
VARIABLE_VALUEAdamax/ec/bias/mQlayer_with_weights-10/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUE
}
VARIABLE_VALUEAdamax/dense_3/kernel/vRlayer_with_weights-6/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
{y
VARIABLE_VALUEAdamax/dense_3/bias/vPlayer_with_weights-6/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
МЙ
VARIABLE_VALUE$Adamax/batch_normalization_3/gamma/vQlayer_with_weights-7/gamma/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
КЗ
VARIABLE_VALUE#Adamax/batch_normalization_3/beta/vPlayer_with_weights-7/beta/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
~|
VARIABLE_VALUEAdamax/hidden/kernel/vRlayer_with_weights-8/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
zx
VARIABLE_VALUEAdamax/hidden/bias/vPlayer_with_weights-8/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
ЖГ
VARIABLE_VALUEAdamax/batch_normalize/gamma/vQlayer_with_weights-9/gamma/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
ДБ
VARIABLE_VALUEAdamax/batch_normalize/beta/vPlayer_with_weights-9/beta/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
{y
VARIABLE_VALUEAdamax/ec/kernel/vSlayer_with_weights-10/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
wu
VARIABLE_VALUEAdamax/ec/bias/vQlayer_with_weights-10/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUE
v
serving_default_dccPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
z
serving_default_exportsPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
z
serving_default_net_dcdPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
v
serving_default_sacPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
v
serving_default_sjrPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
x
serving_default_smscgPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
w
serving_default_tidePlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
П
StatefulPartitionedCallStatefulPartitionedCallserving_default_dccserving_default_exportsserving_default_net_dcdserving_default_sacserving_default_sjrserving_default_smscgserving_default_tideConstConst_1Const_2Const_3Const_4Const_5Const_6Const_7Const_8Const_9Const_10Const_11dense_3/kerneldense_3/bias%batch_normalization_3/moving_variancebatch_normalization_3/gamma!batch_normalization_3/moving_meanbatch_normalization_3/betahidden/kernelhidden/biasbatch_normalize/moving_variancebatch_normalize/gammabatch_normalize/moving_meanbatch_normalize/beta	ec/kernelec/bias*,
Tin%
#2!*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*0
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В */
f*R(
&__inference_signature_wrapper_46413049
O
saver_filenamePlaceholder*
_output_shapes
: *
dtype0*
shape: 
z
StaticRegexFullMatchStaticRegexFullMatchsaver_filename"/device:CPU:**
_output_shapes
: *
pattern
^s3://.*
]
Const_13Const"/device:CPU:**
_output_shapes
: *
dtype0*
valueB B.part
b
Const_14Const"/device:CPU:**
_output_shapes
: *
dtype0*
valueB B
_temp/part
j
SelectSelectStaticRegexFullMatchConst_13Const_14"/device:CPU:**
T0*
_output_shapes
: 
`

StringJoin
StringJoinsaver_filenameSelect"/device:CPU:**
N*
_output_shapes
: 
L

num_shardsConst*
_output_shapes
: *
dtype0*
value	B :
f
ShardedFilename/shardConst"/device:CPU:0*
_output_shapes
: *
dtype0*
value	B : 
x
ShardedFilenameShardedFilename
StringJoinShardedFilename/shard
num_shards"/device:CPU:0*
_output_shapes
: 
ѕ
SaveV2/tensor_namesConst"/device:CPU:0*
_output_shapes
:>*
dtype0*ш
valueоBл>B4layer_with_weights-0/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-0/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-0/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-1/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-1/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-1/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-2/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-2/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-2/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-3/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-3/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-3/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-4/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-4/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-4/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-5/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-5/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-5/count/.ATTRIBUTES/VARIABLE_VALUEB6layer_with_weights-6/kernel/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-6/bias/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-7/gamma/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-7/beta/.ATTRIBUTES/VARIABLE_VALUEB;layer_with_weights-7/moving_mean/.ATTRIBUTES/VARIABLE_VALUEB?layer_with_weights-7/moving_variance/.ATTRIBUTES/VARIABLE_VALUEB6layer_with_weights-8/kernel/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-8/bias/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-9/gamma/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-9/beta/.ATTRIBUTES/VARIABLE_VALUEB;layer_with_weights-9/moving_mean/.ATTRIBUTES/VARIABLE_VALUEB?layer_with_weights-9/moving_variance/.ATTRIBUTES/VARIABLE_VALUEB7layer_with_weights-10/kernel/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-10/bias/.ATTRIBUTES/VARIABLE_VALUEB)optimizer/iter/.ATTRIBUTES/VARIABLE_VALUEB+optimizer/beta_1/.ATTRIBUTES/VARIABLE_VALUEB+optimizer/beta_2/.ATTRIBUTES/VARIABLE_VALUEB*optimizer/decay/.ATTRIBUTES/VARIABLE_VALUEB2optimizer/learning_rate/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/count/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/count/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-6/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-6/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-7/gamma/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-7/beta/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-8/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-8/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-9/gamma/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-9/beta/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBSlayer_with_weights-10/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-10/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-6/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-6/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-7/gamma/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-7/beta/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-8/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-8/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-9/gamma/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-9/beta/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBSlayer_with_weights-10/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-10/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEB_CHECKPOINTABLE_OBJECT_GRAPH
м
SaveV2/shape_and_slicesConst"/device:CPU:0*
_output_shapes
:>*
dtype0*С
valueЗBД>B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B 
л
SaveV2SaveV2ShardedFilenameSaveV2/tensor_namesSaveV2/shape_and_slicesmean/Read/ReadVariableOpvariance/Read/ReadVariableOpcount/Read/ReadVariableOpmean_1/Read/ReadVariableOpvariance_1/Read/ReadVariableOpcount_1/Read/ReadVariableOpmean_2/Read/ReadVariableOpvariance_2/Read/ReadVariableOpcount_2/Read/ReadVariableOpmean_3/Read/ReadVariableOpvariance_3/Read/ReadVariableOpcount_3/Read/ReadVariableOpmean_4/Read/ReadVariableOpvariance_4/Read/ReadVariableOpcount_4/Read/ReadVariableOpmean_5/Read/ReadVariableOpvariance_5/Read/ReadVariableOpcount_5/Read/ReadVariableOp"dense_3/kernel/Read/ReadVariableOp dense_3/bias/Read/ReadVariableOp/batch_normalization_3/gamma/Read/ReadVariableOp.batch_normalization_3/beta/Read/ReadVariableOp5batch_normalization_3/moving_mean/Read/ReadVariableOp9batch_normalization_3/moving_variance/Read/ReadVariableOp!hidden/kernel/Read/ReadVariableOphidden/bias/Read/ReadVariableOp)batch_normalize/gamma/Read/ReadVariableOp(batch_normalize/beta/Read/ReadVariableOp/batch_normalize/moving_mean/Read/ReadVariableOp3batch_normalize/moving_variance/Read/ReadVariableOpec/kernel/Read/ReadVariableOpec/bias/Read/ReadVariableOpAdamax/iter/Read/ReadVariableOp!Adamax/beta_1/Read/ReadVariableOp!Adamax/beta_2/Read/ReadVariableOp Adamax/decay/Read/ReadVariableOp(Adamax/learning_rate/Read/ReadVariableOptotal/Read/ReadVariableOpcount_6/Read/ReadVariableOptotal_1/Read/ReadVariableOpcount_7/Read/ReadVariableOp+Adamax/dense_3/kernel/m/Read/ReadVariableOp)Adamax/dense_3/bias/m/Read/ReadVariableOp8Adamax/batch_normalization_3/gamma/m/Read/ReadVariableOp7Adamax/batch_normalization_3/beta/m/Read/ReadVariableOp*Adamax/hidden/kernel/m/Read/ReadVariableOp(Adamax/hidden/bias/m/Read/ReadVariableOp2Adamax/batch_normalize/gamma/m/Read/ReadVariableOp1Adamax/batch_normalize/beta/m/Read/ReadVariableOp&Adamax/ec/kernel/m/Read/ReadVariableOp$Adamax/ec/bias/m/Read/ReadVariableOp+Adamax/dense_3/kernel/v/Read/ReadVariableOp)Adamax/dense_3/bias/v/Read/ReadVariableOp8Adamax/batch_normalization_3/gamma/v/Read/ReadVariableOp7Adamax/batch_normalization_3/beta/v/Read/ReadVariableOp*Adamax/hidden/kernel/v/Read/ReadVariableOp(Adamax/hidden/bias/v/Read/ReadVariableOp2Adamax/batch_normalize/gamma/v/Read/ReadVariableOp1Adamax/batch_normalize/beta/v/Read/ReadVariableOp&Adamax/ec/kernel/v/Read/ReadVariableOp$Adamax/ec/bias/v/Read/ReadVariableOpConst_12"/device:CPU:0*L
dtypesB
@2>							
Е
&MergeV2Checkpoints/checkpoint_prefixesPackShardedFilename^SaveV2"/device:CPU:0*
N*
T0*
_output_shapes
:
o
MergeV2CheckpointsMergeV2Checkpoints&MergeV2Checkpoints/checkpoint_prefixessaver_filename"/device:CPU:0
i
IdentityIdentitysaver_filename^MergeV2Checkpoints"/device:CPU:0*
T0*
_output_shapes
: 
“
RestoreV2/tensor_namesConst"/device:CPU:0*
_output_shapes
:>*
dtype0*ш
valueоBл>B4layer_with_weights-0/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-0/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-0/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-1/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-1/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-1/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-2/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-2/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-2/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-3/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-3/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-3/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-4/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-4/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-4/count/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-5/mean/.ATTRIBUTES/VARIABLE_VALUEB8layer_with_weights-5/variance/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-5/count/.ATTRIBUTES/VARIABLE_VALUEB6layer_with_weights-6/kernel/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-6/bias/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-7/gamma/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-7/beta/.ATTRIBUTES/VARIABLE_VALUEB;layer_with_weights-7/moving_mean/.ATTRIBUTES/VARIABLE_VALUEB?layer_with_weights-7/moving_variance/.ATTRIBUTES/VARIABLE_VALUEB6layer_with_weights-8/kernel/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-8/bias/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-9/gamma/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-9/beta/.ATTRIBUTES/VARIABLE_VALUEB;layer_with_weights-9/moving_mean/.ATTRIBUTES/VARIABLE_VALUEB?layer_with_weights-9/moving_variance/.ATTRIBUTES/VARIABLE_VALUEB7layer_with_weights-10/kernel/.ATTRIBUTES/VARIABLE_VALUEB5layer_with_weights-10/bias/.ATTRIBUTES/VARIABLE_VALUEB)optimizer/iter/.ATTRIBUTES/VARIABLE_VALUEB+optimizer/beta_1/.ATTRIBUTES/VARIABLE_VALUEB+optimizer/beta_2/.ATTRIBUTES/VARIABLE_VALUEB*optimizer/decay/.ATTRIBUTES/VARIABLE_VALUEB2optimizer/learning_rate/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/count/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/count/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-6/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-6/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-7/gamma/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-7/beta/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-8/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-8/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-9/gamma/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-9/beta/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBSlayer_with_weights-10/kernel/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-10/bias/.OPTIMIZER_SLOT/optimizer/m/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-6/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-6/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-7/gamma/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-7/beta/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBRlayer_with_weights-8/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-8/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-9/gamma/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBPlayer_with_weights-9/beta/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBSlayer_with_weights-10/kernel/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEBQlayer_with_weights-10/bias/.OPTIMIZER_SLOT/optimizer/v/.ATTRIBUTES/VARIABLE_VALUEB_CHECKPOINTABLE_OBJECT_GRAPH
п
RestoreV2/shape_and_slicesConst"/device:CPU:0*
_output_shapes
:>*
dtype0*С
valueЗBД>B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B B 
»
	RestoreV2	RestoreV2saver_filenameRestoreV2/tensor_namesRestoreV2/shape_and_slices"/device:CPU:0*О
_output_shapesы
ш::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*L
dtypesB
@2>							
S

Identity_1Identity	RestoreV2"/device:CPU:0*
T0*
_output_shapes
:
R
AssignVariableOpAssignVariableOpmean
Identity_1"/device:CPU:0*
dtype0
U

Identity_2IdentityRestoreV2:1"/device:CPU:0*
T0*
_output_shapes
:
X
AssignVariableOp_1AssignVariableOpvariance
Identity_2"/device:CPU:0*
dtype0
U

Identity_3IdentityRestoreV2:2"/device:CPU:0*
T0	*
_output_shapes
:
U
AssignVariableOp_2AssignVariableOpcount
Identity_3"/device:CPU:0*
dtype0	
U

Identity_4IdentityRestoreV2:3"/device:CPU:0*
T0*
_output_shapes
:
V
AssignVariableOp_3AssignVariableOpmean_1
Identity_4"/device:CPU:0*
dtype0
U

Identity_5IdentityRestoreV2:4"/device:CPU:0*
T0*
_output_shapes
:
Z
AssignVariableOp_4AssignVariableOp
variance_1
Identity_5"/device:CPU:0*
dtype0
U

Identity_6IdentityRestoreV2:5"/device:CPU:0*
T0	*
_output_shapes
:
W
AssignVariableOp_5AssignVariableOpcount_1
Identity_6"/device:CPU:0*
dtype0	
U

Identity_7IdentityRestoreV2:6"/device:CPU:0*
T0*
_output_shapes
:
V
AssignVariableOp_6AssignVariableOpmean_2
Identity_7"/device:CPU:0*
dtype0
U

Identity_8IdentityRestoreV2:7"/device:CPU:0*
T0*
_output_shapes
:
Z
AssignVariableOp_7AssignVariableOp
variance_2
Identity_8"/device:CPU:0*
dtype0
U

Identity_9IdentityRestoreV2:8"/device:CPU:0*
T0	*
_output_shapes
:
W
AssignVariableOp_8AssignVariableOpcount_2
Identity_9"/device:CPU:0*
dtype0	
V
Identity_10IdentityRestoreV2:9"/device:CPU:0*
T0*
_output_shapes
:
W
AssignVariableOp_9AssignVariableOpmean_3Identity_10"/device:CPU:0*
dtype0
W
Identity_11IdentityRestoreV2:10"/device:CPU:0*
T0*
_output_shapes
:
\
AssignVariableOp_10AssignVariableOp
variance_3Identity_11"/device:CPU:0*
dtype0
W
Identity_12IdentityRestoreV2:11"/device:CPU:0*
T0	*
_output_shapes
:
Y
AssignVariableOp_11AssignVariableOpcount_3Identity_12"/device:CPU:0*
dtype0	
W
Identity_13IdentityRestoreV2:12"/device:CPU:0*
T0*
_output_shapes
:
X
AssignVariableOp_12AssignVariableOpmean_4Identity_13"/device:CPU:0*
dtype0
W
Identity_14IdentityRestoreV2:13"/device:CPU:0*
T0*
_output_shapes
:
\
AssignVariableOp_13AssignVariableOp
variance_4Identity_14"/device:CPU:0*
dtype0
W
Identity_15IdentityRestoreV2:14"/device:CPU:0*
T0	*
_output_shapes
:
Y
AssignVariableOp_14AssignVariableOpcount_4Identity_15"/device:CPU:0*
dtype0	
W
Identity_16IdentityRestoreV2:15"/device:CPU:0*
T0*
_output_shapes
:
X
AssignVariableOp_15AssignVariableOpmean_5Identity_16"/device:CPU:0*
dtype0
W
Identity_17IdentityRestoreV2:16"/device:CPU:0*
T0*
_output_shapes
:
\
AssignVariableOp_16AssignVariableOp
variance_5Identity_17"/device:CPU:0*
dtype0
W
Identity_18IdentityRestoreV2:17"/device:CPU:0*
T0	*
_output_shapes
:
Y
AssignVariableOp_17AssignVariableOpcount_5Identity_18"/device:CPU:0*
dtype0	
W
Identity_19IdentityRestoreV2:18"/device:CPU:0*
T0*
_output_shapes
:
`
AssignVariableOp_18AssignVariableOpdense_3/kernelIdentity_19"/device:CPU:0*
dtype0
W
Identity_20IdentityRestoreV2:19"/device:CPU:0*
T0*
_output_shapes
:
^
AssignVariableOp_19AssignVariableOpdense_3/biasIdentity_20"/device:CPU:0*
dtype0
W
Identity_21IdentityRestoreV2:20"/device:CPU:0*
T0*
_output_shapes
:
m
AssignVariableOp_20AssignVariableOpbatch_normalization_3/gammaIdentity_21"/device:CPU:0*
dtype0
W
Identity_22IdentityRestoreV2:21"/device:CPU:0*
T0*
_output_shapes
:
l
AssignVariableOp_21AssignVariableOpbatch_normalization_3/betaIdentity_22"/device:CPU:0*
dtype0
W
Identity_23IdentityRestoreV2:22"/device:CPU:0*
T0*
_output_shapes
:
s
AssignVariableOp_22AssignVariableOp!batch_normalization_3/moving_meanIdentity_23"/device:CPU:0*
dtype0
W
Identity_24IdentityRestoreV2:23"/device:CPU:0*
T0*
_output_shapes
:
w
AssignVariableOp_23AssignVariableOp%batch_normalization_3/moving_varianceIdentity_24"/device:CPU:0*
dtype0
W
Identity_25IdentityRestoreV2:24"/device:CPU:0*
T0*
_output_shapes
:
_
AssignVariableOp_24AssignVariableOphidden/kernelIdentity_25"/device:CPU:0*
dtype0
W
Identity_26IdentityRestoreV2:25"/device:CPU:0*
T0*
_output_shapes
:
]
AssignVariableOp_25AssignVariableOphidden/biasIdentity_26"/device:CPU:0*
dtype0
W
Identity_27IdentityRestoreV2:26"/device:CPU:0*
T0*
_output_shapes
:
g
AssignVariableOp_26AssignVariableOpbatch_normalize/gammaIdentity_27"/device:CPU:0*
dtype0
W
Identity_28IdentityRestoreV2:27"/device:CPU:0*
T0*
_output_shapes
:
f
AssignVariableOp_27AssignVariableOpbatch_normalize/betaIdentity_28"/device:CPU:0*
dtype0
W
Identity_29IdentityRestoreV2:28"/device:CPU:0*
T0*
_output_shapes
:
m
AssignVariableOp_28AssignVariableOpbatch_normalize/moving_meanIdentity_29"/device:CPU:0*
dtype0
W
Identity_30IdentityRestoreV2:29"/device:CPU:0*
T0*
_output_shapes
:
q
AssignVariableOp_29AssignVariableOpbatch_normalize/moving_varianceIdentity_30"/device:CPU:0*
dtype0
W
Identity_31IdentityRestoreV2:30"/device:CPU:0*
T0*
_output_shapes
:
[
AssignVariableOp_30AssignVariableOp	ec/kernelIdentity_31"/device:CPU:0*
dtype0
W
Identity_32IdentityRestoreV2:31"/device:CPU:0*
T0*
_output_shapes
:
Y
AssignVariableOp_31AssignVariableOpec/biasIdentity_32"/device:CPU:0*
dtype0
W
Identity_33IdentityRestoreV2:32"/device:CPU:0*
T0	*
_output_shapes
:
]
AssignVariableOp_32AssignVariableOpAdamax/iterIdentity_33"/device:CPU:0*
dtype0	
W
Identity_34IdentityRestoreV2:33"/device:CPU:0*
T0*
_output_shapes
:
_
AssignVariableOp_33AssignVariableOpAdamax/beta_1Identity_34"/device:CPU:0*
dtype0
W
Identity_35IdentityRestoreV2:34"/device:CPU:0*
T0*
_output_shapes
:
_
AssignVariableOp_34AssignVariableOpAdamax/beta_2Identity_35"/device:CPU:0*
dtype0
W
Identity_36IdentityRestoreV2:35"/device:CPU:0*
T0*
_output_shapes
:
^
AssignVariableOp_35AssignVariableOpAdamax/decayIdentity_36"/device:CPU:0*
dtype0
W
Identity_37IdentityRestoreV2:36"/device:CPU:0*
T0*
_output_shapes
:
f
AssignVariableOp_36AssignVariableOpAdamax/learning_rateIdentity_37"/device:CPU:0*
dtype0
W
Identity_38IdentityRestoreV2:37"/device:CPU:0*
T0*
_output_shapes
:
W
AssignVariableOp_37AssignVariableOptotalIdentity_38"/device:CPU:0*
dtype0
W
Identity_39IdentityRestoreV2:38"/device:CPU:0*
T0*
_output_shapes
:
Y
AssignVariableOp_38AssignVariableOpcount_6Identity_39"/device:CPU:0*
dtype0
W
Identity_40IdentityRestoreV2:39"/device:CPU:0*
T0*
_output_shapes
:
Y
AssignVariableOp_39AssignVariableOptotal_1Identity_40"/device:CPU:0*
dtype0
W
Identity_41IdentityRestoreV2:40"/device:CPU:0*
T0*
_output_shapes
:
Y
AssignVariableOp_40AssignVariableOpcount_7Identity_41"/device:CPU:0*
dtype0
W
Identity_42IdentityRestoreV2:41"/device:CPU:0*
T0*
_output_shapes
:
i
AssignVariableOp_41AssignVariableOpAdamax/dense_3/kernel/mIdentity_42"/device:CPU:0*
dtype0
W
Identity_43IdentityRestoreV2:42"/device:CPU:0*
T0*
_output_shapes
:
g
AssignVariableOp_42AssignVariableOpAdamax/dense_3/bias/mIdentity_43"/device:CPU:0*
dtype0
W
Identity_44IdentityRestoreV2:43"/device:CPU:0*
T0*
_output_shapes
:
v
AssignVariableOp_43AssignVariableOp$Adamax/batch_normalization_3/gamma/mIdentity_44"/device:CPU:0*
dtype0
W
Identity_45IdentityRestoreV2:44"/device:CPU:0*
T0*
_output_shapes
:
u
AssignVariableOp_44AssignVariableOp#Adamax/batch_normalization_3/beta/mIdentity_45"/device:CPU:0*
dtype0
W
Identity_46IdentityRestoreV2:45"/device:CPU:0*
T0*
_output_shapes
:
h
AssignVariableOp_45AssignVariableOpAdamax/hidden/kernel/mIdentity_46"/device:CPU:0*
dtype0
W
Identity_47IdentityRestoreV2:46"/device:CPU:0*
T0*
_output_shapes
:
f
AssignVariableOp_46AssignVariableOpAdamax/hidden/bias/mIdentity_47"/device:CPU:0*
dtype0
W
Identity_48IdentityRestoreV2:47"/device:CPU:0*
T0*
_output_shapes
:
p
AssignVariableOp_47AssignVariableOpAdamax/batch_normalize/gamma/mIdentity_48"/device:CPU:0*
dtype0
W
Identity_49IdentityRestoreV2:48"/device:CPU:0*
T0*
_output_shapes
:
o
AssignVariableOp_48AssignVariableOpAdamax/batch_normalize/beta/mIdentity_49"/device:CPU:0*
dtype0
W
Identity_50IdentityRestoreV2:49"/device:CPU:0*
T0*
_output_shapes
:
d
AssignVariableOp_49AssignVariableOpAdamax/ec/kernel/mIdentity_50"/device:CPU:0*
dtype0
W
Identity_51IdentityRestoreV2:50"/device:CPU:0*
T0*
_output_shapes
:
b
AssignVariableOp_50AssignVariableOpAdamax/ec/bias/mIdentity_51"/device:CPU:0*
dtype0
W
Identity_52IdentityRestoreV2:51"/device:CPU:0*
T0*
_output_shapes
:
i
AssignVariableOp_51AssignVariableOpAdamax/dense_3/kernel/vIdentity_52"/device:CPU:0*
dtype0
W
Identity_53IdentityRestoreV2:52"/device:CPU:0*
T0*
_output_shapes
:
g
AssignVariableOp_52AssignVariableOpAdamax/dense_3/bias/vIdentity_53"/device:CPU:0*
dtype0
W
Identity_54IdentityRestoreV2:53"/device:CPU:0*
T0*
_output_shapes
:
v
AssignVariableOp_53AssignVariableOp$Adamax/batch_normalization_3/gamma/vIdentity_54"/device:CPU:0*
dtype0
W
Identity_55IdentityRestoreV2:54"/device:CPU:0*
T0*
_output_shapes
:
u
AssignVariableOp_54AssignVariableOp#Adamax/batch_normalization_3/beta/vIdentity_55"/device:CPU:0*
dtype0
W
Identity_56IdentityRestoreV2:55"/device:CPU:0*
T0*
_output_shapes
:
h
AssignVariableOp_55AssignVariableOpAdamax/hidden/kernel/vIdentity_56"/device:CPU:0*
dtype0
W
Identity_57IdentityRestoreV2:56"/device:CPU:0*
T0*
_output_shapes
:
f
AssignVariableOp_56AssignVariableOpAdamax/hidden/bias/vIdentity_57"/device:CPU:0*
dtype0
W
Identity_58IdentityRestoreV2:57"/device:CPU:0*
T0*
_output_shapes
:
p
AssignVariableOp_57AssignVariableOpAdamax/batch_normalize/gamma/vIdentity_58"/device:CPU:0*
dtype0
W
Identity_59IdentityRestoreV2:58"/device:CPU:0*
T0*
_output_shapes
:
o
AssignVariableOp_58AssignVariableOpAdamax/batch_normalize/beta/vIdentity_59"/device:CPU:0*
dtype0
W
Identity_60IdentityRestoreV2:59"/device:CPU:0*
T0*
_output_shapes
:
d
AssignVariableOp_59AssignVariableOpAdamax/ec/kernel/vIdentity_60"/device:CPU:0*
dtype0
W
Identity_61IdentityRestoreV2:60"/device:CPU:0*
T0*
_output_shapes
:
b
AssignVariableOp_60AssignVariableOpAdamax/ec/bias/vIdentity_61"/device:CPU:0*
dtype0

NoOp_1NoOp"/device:CPU:0
Т
Identity_62Identitysaver_filename^AssignVariableOp^AssignVariableOp_1^AssignVariableOp_10^AssignVariableOp_11^AssignVariableOp_12^AssignVariableOp_13^AssignVariableOp_14^AssignVariableOp_15^AssignVariableOp_16^AssignVariableOp_17^AssignVariableOp_18^AssignVariableOp_19^AssignVariableOp_2^AssignVariableOp_20^AssignVariableOp_21^AssignVariableOp_22^AssignVariableOp_23^AssignVariableOp_24^AssignVariableOp_25^AssignVariableOp_26^AssignVariableOp_27^AssignVariableOp_28^AssignVariableOp_29^AssignVariableOp_3^AssignVariableOp_30^AssignVariableOp_31^AssignVariableOp_32^AssignVariableOp_33^AssignVariableOp_34^AssignVariableOp_35^AssignVariableOp_36^AssignVariableOp_37^AssignVariableOp_38^AssignVariableOp_39^AssignVariableOp_4^AssignVariableOp_40^AssignVariableOp_41^AssignVariableOp_42^AssignVariableOp_43^AssignVariableOp_44^AssignVariableOp_45^AssignVariableOp_46^AssignVariableOp_47^AssignVariableOp_48^AssignVariableOp_49^AssignVariableOp_5^AssignVariableOp_50^AssignVariableOp_51^AssignVariableOp_52^AssignVariableOp_53^AssignVariableOp_54^AssignVariableOp_55^AssignVariableOp_56^AssignVariableOp_57^AssignVariableOp_58^AssignVariableOp_59^AssignVariableOp_6^AssignVariableOp_60^AssignVariableOp_7^AssignVariableOp_8^AssignVariableOp_9^NoOp_1"/device:CPU:0*
T0*
_output_shapes
: ог
у$
Ћ
2__inference_batch_normalize_layer_call_fn_46413795

inputs5
'assignmovingavg_readvariableop_resource:7
)assignmovingavg_1_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:/
!batchnorm_readvariableop_resource:
identityИҐAssignMovingAvgҐAssignMovingAvg/ReadVariableOpҐAssignMovingAvg_1Ґ AssignMovingAvg_1/ReadVariableOpҐbatchnorm/ReadVariableOpҐbatchnorm/mul/ReadVariableOph
moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: 
moments/meanMeaninputs'moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(d
moments/StopGradientStopGradientmoments/mean:output:0*
T0*
_output_shapes

:З
moments/SquaredDifferenceSquaredDifferenceinputsmoments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€l
"moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ю
moments/varianceMeanmoments/SquaredDifference:z:0+moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(m
moments/SqueezeSqueezemoments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 s
moments/Squeeze_1Squeezemoments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Z
AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<В
AssignMovingAvg/ReadVariableOpReadVariableOp'assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0Б
AssignMovingAvg/subSub&AssignMovingAvg/ReadVariableOp:value:0moments/Squeeze:output:0*
T0*
_output_shapes
:x
AssignMovingAvg/mulMulAssignMovingAvg/sub:z:0AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:ђ
AssignMovingAvgAssignSubVariableOp'assignmovingavg_readvariableop_resourceAssignMovingAvg/mul:z:0^AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0\
AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ж
 AssignMovingAvg_1/ReadVariableOpReadVariableOp)assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0З
AssignMovingAvg_1/subSub(AssignMovingAvg_1/ReadVariableOp:value:0moments/Squeeze_1:output:0*
T0*
_output_shapes
:~
AssignMovingAvg_1/mulMulAssignMovingAvg_1/sub:z:0 AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:і
AssignMovingAvg_1AssignSubVariableOp)assignmovingavg_1_readvariableop_resourceAssignMovingAvg_1/mul:z:0!^AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:q
batchnorm/addAddV2moments/Squeeze_1:output:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€h
batchnorm/mul_2Mulmoments/Squeeze:output:0batchnorm/mul:z:0*
T0*
_output_shapes
:v
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0p
batchnorm/subSub batchnorm/ReadVariableOp:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€к
NoOpNoOp^AssignMovingAvg^AssignMovingAvg/ReadVariableOp^AssignMovingAvg_1!^AssignMovingAvg_1/ReadVariableOp^batchnorm/ReadVariableOp^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 2"
AssignMovingAvgAssignMovingAvg2@
AssignMovingAvg/ReadVariableOpAssignMovingAvg/ReadVariableOp2&
AssignMovingAvg_1AssignMovingAvg_12D
 AssignMovingAvg_1/ReadVariableOp AssignMovingAvg_1/ReadVariableOp24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp2<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Я©
г
*__inference_model_3_layer_call_fn_46413299
inputs_0
inputs_1
inputs_2
inputs_3
inputs_4
inputs_5
inputs_6
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:K
=batch_normalization_3_assignmovingavg_readvariableop_resource:M
?batch_normalization_3_assignmovingavg_1_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:E
7batch_normalize_assignmovingavg_readvariableop_resource:G
9batch_normalize_assignmovingavg_1_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ%batch_normalization_3/AssignMovingAvgҐ4batch_normalization_3/AssignMovingAvg/ReadVariableOpҐ'batch_normalization_3/AssignMovingAvg_1Ґ6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐbatch_normalize/AssignMovingAvgҐ.batch_normalize/AssignMovingAvg/ReadVariableOpҐ!batch_normalize/AssignMovingAvg_1Ґ0batch_normalize/AssignMovingAvg_1/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    o
rescaling_3/mulMulinputs_0rescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_18/subSubinputs_1normalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_19/subSubinputs_2normalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_20/subSubinputs_3normalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_21/subSubinputs_4normalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_22/subSubinputs_5normalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_23/subSubinputs_6normalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
4batch_normalization_3/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Є
"batch_normalization_3/moments/meanMeandense_3/Sigmoid:y:0=batch_normalization_3/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Р
*batch_normalization_3/moments/StopGradientStopGradient+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes

:ј
/batch_normalization_3/moments/SquaredDifferenceSquaredDifferencedense_3/Sigmoid:y:03batch_normalization_3/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€В
8batch_normalization_3/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: а
&batch_normalization_3/moments/varianceMean3batch_normalization_3/moments/SquaredDifference:z:0Abatch_normalization_3/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Щ
%batch_normalization_3/moments/SqueezeSqueeze+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Я
'batch_normalization_3/moments/Squeeze_1Squeeze/batch_normalization_3/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 p
+batch_normalization_3/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ѓ
4batch_normalization_3/AssignMovingAvg/ReadVariableOpReadVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0√
)batch_normalization_3/AssignMovingAvg/subSub<batch_normalization_3/AssignMovingAvg/ReadVariableOp:value:0.batch_normalization_3/moments/Squeeze:output:0*
T0*
_output_shapes
:Ї
)batch_normalization_3/AssignMovingAvg/mulMul-batch_normalization_3/AssignMovingAvg/sub:z:04batch_normalization_3/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:Д
%batch_normalization_3/AssignMovingAvgAssignSubVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource-batch_normalization_3/AssignMovingAvg/mul:z:05^batch_normalization_3/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0r
-batch_normalization_3/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<≤
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpReadVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0…
+batch_normalization_3/AssignMovingAvg_1/subSub>batch_normalization_3/AssignMovingAvg_1/ReadVariableOp:value:00batch_normalization_3/moments/Squeeze_1:output:0*
T0*
_output_shapes
:ј
+batch_normalization_3/AssignMovingAvg_1/mulMul/batch_normalization_3/AssignMovingAvg_1/sub:z:06batch_normalization_3/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:М
'batch_normalization_3/AssignMovingAvg_1AssignSubVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource/batch_normalization_3/AssignMovingAvg_1/mul:z:07^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:≥
#batch_normalization_3/batchnorm/addAddV20batch_normalization_3/moments/Squeeze_1:output:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€™
%batch_normalization_3/batchnorm/mul_2Mul.batch_normalization_3/moments/Squeeze:output:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0≤
#batch_normalization_3/batchnorm/subSub6batch_normalization_3/batchnorm/ReadVariableOp:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€x
.batch_normalize/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ђ
batch_normalize/moments/meanMeanhidden/Sigmoid:y:07batch_normalize/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Д
$batch_normalize/moments/StopGradientStopGradient%batch_normalize/moments/mean:output:0*
T0*
_output_shapes

:≥
)batch_normalize/moments/SquaredDifferenceSquaredDifferencehidden/Sigmoid:y:0-batch_normalize/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€|
2batch_normalize/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: ќ
 batch_normalize/moments/varianceMean-batch_normalize/moments/SquaredDifference:z:0;batch_normalize/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Н
batch_normalize/moments/SqueezeSqueeze%batch_normalize/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 У
!batch_normalize/moments/Squeeze_1Squeeze)batch_normalize/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 j
%batch_normalize/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ґ
.batch_normalize/AssignMovingAvg/ReadVariableOpReadVariableOp7batch_normalize_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0±
#batch_normalize/AssignMovingAvg/subSub6batch_normalize/AssignMovingAvg/ReadVariableOp:value:0(batch_normalize/moments/Squeeze:output:0*
T0*
_output_shapes
:®
#batch_normalize/AssignMovingAvg/mulMul'batch_normalize/AssignMovingAvg/sub:z:0.batch_normalize/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:м
batch_normalize/AssignMovingAvgAssignSubVariableOp7batch_normalize_assignmovingavg_readvariableop_resource'batch_normalize/AssignMovingAvg/mul:z:0/^batch_normalize/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0l
'batch_normalize/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<¶
0batch_normalize/AssignMovingAvg_1/ReadVariableOpReadVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0Ј
%batch_normalize/AssignMovingAvg_1/subSub8batch_normalize/AssignMovingAvg_1/ReadVariableOp:value:0*batch_normalize/moments/Squeeze_1:output:0*
T0*
_output_shapes
:Ѓ
%batch_normalize/AssignMovingAvg_1/mulMul)batch_normalize/AssignMovingAvg_1/sub:z:00batch_normalize/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:ф
!batch_normalize/AssignMovingAvg_1AssignSubVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource)batch_normalize/AssignMovingAvg_1/mul:z:01^batch_normalize/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:°
batch_normalize/batchnorm/addAddV2*batch_normalize/moments/Squeeze_1:output:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ш
batch_normalize/batchnorm/mul_2Mul(batch_normalize/moments/Squeeze:output:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0†
batch_normalize/batchnorm/subSub0batch_normalize/batchnorm/ReadVariableOp:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€©
NoOpNoOp&^batch_normalization_3/AssignMovingAvg5^batch_normalization_3/AssignMovingAvg/ReadVariableOp(^batch_normalization_3/AssignMovingAvg_17^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp/^batch_normalization_3/batchnorm/ReadVariableOp3^batch_normalization_3/batchnorm/mul/ReadVariableOp ^batch_normalize/AssignMovingAvg/^batch_normalize/AssignMovingAvg/ReadVariableOp"^batch_normalize/AssignMovingAvg_11^batch_normalize/AssignMovingAvg_1/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2N
%batch_normalization_3/AssignMovingAvg%batch_normalization_3/AssignMovingAvg2l
4batch_normalization_3/AssignMovingAvg/ReadVariableOp4batch_normalization_3/AssignMovingAvg/ReadVariableOp2R
'batch_normalization_3/AssignMovingAvg_1'batch_normalization_3/AssignMovingAvg_12p
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2B
batch_normalize/AssignMovingAvgbatch_normalize/AssignMovingAvg2`
.batch_normalize/AssignMovingAvg/ReadVariableOp.batch_normalize/AssignMovingAvg/ReadVariableOp2F
!batch_normalize/AssignMovingAvg_1!batch_normalize/AssignMovingAvg_12d
0batch_normalize/AssignMovingAvg_1/ReadVariableOp0batch_normalize/AssignMovingAvg_1/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:Q M
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/0:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/1:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/2:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/3:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/4:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/5:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/6:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
–
≤
S__inference_batch_normalization_3_layer_call_and_return_conditional_losses_46413685

inputs/
!batchnorm_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:1
#batchnorm_readvariableop_1_resource:1
#batchnorm_readvariableop_2_resource:
identityИҐbatchnorm/ReadVariableOpҐbatchnorm/ReadVariableOp_1Ґbatchnorm/ReadVariableOp_2Ґbatchnorm/mul/ReadVariableOpv
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:w
batchnorm/addAddV2 batchnorm/ReadVariableOp:value:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
batchnorm/ReadVariableOp_1ReadVariableOp#batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0r
batchnorm/mul_2Mul"batchnorm/ReadVariableOp_1:value:0batchnorm/mul:z:0*
T0*
_output_shapes
:z
batchnorm/ReadVariableOp_2ReadVariableOp#batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0r
batchnorm/subSub"batchnorm/ReadVariableOp_2:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€Ї
NoOpNoOp^batchnorm/ReadVariableOp^batchnorm/ReadVariableOp_1^batchnorm/ReadVariableOp_2^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp28
batchnorm/ReadVariableOp_1batchnorm/ReadVariableOp_128
batchnorm/ReadVariableOp_2batchnorm/ReadVariableOp_22<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
…
e
I__inference_rescaling_3_layer_call_and_return_conditional_losses_46413565

inputs
identityK
Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7M
Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    U
mulMulinputsCast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
addAddV2mul:z:0Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€O
IdentityIdentityadd:z:0*
T0*'
_output_shapes
:€€€€€€€€€"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*&
_input_shapes
:€€€€€€€€€:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
А

џ
*__inference_dense_3_layer_call_fn_46413600

inputs0
matmul_readvariableop_resource:~-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:~*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
SigmoidSigmoidBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
IdentityIdentitySigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€~: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€~
 
_user_specified_nameinputs
„®
Ћ
*__inference_model_3_layer_call_fn_46412728
sac
exports
dcc
net_dcd
sjr
tide	
smscg
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:K
=batch_normalization_3_assignmovingavg_readvariableop_resource:M
?batch_normalization_3_assignmovingavg_1_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:E
7batch_normalize_assignmovingavg_readvariableop_resource:G
9batch_normalize_assignmovingavg_1_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ%batch_normalization_3/AssignMovingAvgҐ4batch_normalization_3/AssignMovingAvg/ReadVariableOpҐ'batch_normalization_3/AssignMovingAvg_1Ґ6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐbatch_normalize/AssignMovingAvgҐ.batch_normalize/AssignMovingAvg/ReadVariableOpҐ!batch_normalize/AssignMovingAvg_1Ґ0batch_normalize/AssignMovingAvg_1/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    j
rescaling_3/mulMulsacrescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_18/subSubexportsnormalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_19/subSubdccnormalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_20/subSubnet_dcdnormalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_21/subSubsjrnormalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€k
normalization_22/subSubtidenormalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€l
normalization_23/subSubsmscgnormalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
4batch_normalization_3/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Є
"batch_normalization_3/moments/meanMeandense_3/Sigmoid:y:0=batch_normalization_3/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Р
*batch_normalization_3/moments/StopGradientStopGradient+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes

:ј
/batch_normalization_3/moments/SquaredDifferenceSquaredDifferencedense_3/Sigmoid:y:03batch_normalization_3/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€В
8batch_normalization_3/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: а
&batch_normalization_3/moments/varianceMean3batch_normalization_3/moments/SquaredDifference:z:0Abatch_normalization_3/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Щ
%batch_normalization_3/moments/SqueezeSqueeze+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Я
'batch_normalization_3/moments/Squeeze_1Squeeze/batch_normalization_3/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 p
+batch_normalization_3/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ѓ
4batch_normalization_3/AssignMovingAvg/ReadVariableOpReadVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0√
)batch_normalization_3/AssignMovingAvg/subSub<batch_normalization_3/AssignMovingAvg/ReadVariableOp:value:0.batch_normalization_3/moments/Squeeze:output:0*
T0*
_output_shapes
:Ї
)batch_normalization_3/AssignMovingAvg/mulMul-batch_normalization_3/AssignMovingAvg/sub:z:04batch_normalization_3/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:Д
%batch_normalization_3/AssignMovingAvgAssignSubVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource-batch_normalization_3/AssignMovingAvg/mul:z:05^batch_normalization_3/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0r
-batch_normalization_3/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<≤
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpReadVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0…
+batch_normalization_3/AssignMovingAvg_1/subSub>batch_normalization_3/AssignMovingAvg_1/ReadVariableOp:value:00batch_normalization_3/moments/Squeeze_1:output:0*
T0*
_output_shapes
:ј
+batch_normalization_3/AssignMovingAvg_1/mulMul/batch_normalization_3/AssignMovingAvg_1/sub:z:06batch_normalization_3/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:М
'batch_normalization_3/AssignMovingAvg_1AssignSubVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource/batch_normalization_3/AssignMovingAvg_1/mul:z:07^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:≥
#batch_normalization_3/batchnorm/addAddV20batch_normalization_3/moments/Squeeze_1:output:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€™
%batch_normalization_3/batchnorm/mul_2Mul.batch_normalization_3/moments/Squeeze:output:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0≤
#batch_normalization_3/batchnorm/subSub6batch_normalization_3/batchnorm/ReadVariableOp:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€x
.batch_normalize/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ђ
batch_normalize/moments/meanMeanhidden/Sigmoid:y:07batch_normalize/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Д
$batch_normalize/moments/StopGradientStopGradient%batch_normalize/moments/mean:output:0*
T0*
_output_shapes

:≥
)batch_normalize/moments/SquaredDifferenceSquaredDifferencehidden/Sigmoid:y:0-batch_normalize/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€|
2batch_normalize/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: ќ
 batch_normalize/moments/varianceMean-batch_normalize/moments/SquaredDifference:z:0;batch_normalize/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Н
batch_normalize/moments/SqueezeSqueeze%batch_normalize/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 У
!batch_normalize/moments/Squeeze_1Squeeze)batch_normalize/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 j
%batch_normalize/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ґ
.batch_normalize/AssignMovingAvg/ReadVariableOpReadVariableOp7batch_normalize_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0±
#batch_normalize/AssignMovingAvg/subSub6batch_normalize/AssignMovingAvg/ReadVariableOp:value:0(batch_normalize/moments/Squeeze:output:0*
T0*
_output_shapes
:®
#batch_normalize/AssignMovingAvg/mulMul'batch_normalize/AssignMovingAvg/sub:z:0.batch_normalize/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:м
batch_normalize/AssignMovingAvgAssignSubVariableOp7batch_normalize_assignmovingavg_readvariableop_resource'batch_normalize/AssignMovingAvg/mul:z:0/^batch_normalize/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0l
'batch_normalize/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<¶
0batch_normalize/AssignMovingAvg_1/ReadVariableOpReadVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0Ј
%batch_normalize/AssignMovingAvg_1/subSub8batch_normalize/AssignMovingAvg_1/ReadVariableOp:value:0*batch_normalize/moments/Squeeze_1:output:0*
T0*
_output_shapes
:Ѓ
%batch_normalize/AssignMovingAvg_1/mulMul)batch_normalize/AssignMovingAvg_1/sub:z:00batch_normalize/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:ф
!batch_normalize/AssignMovingAvg_1AssignSubVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource)batch_normalize/AssignMovingAvg_1/mul:z:01^batch_normalize/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:°
batch_normalize/batchnorm/addAddV2*batch_normalize/moments/Squeeze_1:output:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ш
batch_normalize/batchnorm/mul_2Mul(batch_normalize/moments/Squeeze:output:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0†
batch_normalize/batchnorm/subSub0batch_normalize/batchnorm/ReadVariableOp:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€©
NoOpNoOp&^batch_normalization_3/AssignMovingAvg5^batch_normalization_3/AssignMovingAvg/ReadVariableOp(^batch_normalization_3/AssignMovingAvg_17^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp/^batch_normalization_3/batchnorm/ReadVariableOp3^batch_normalization_3/batchnorm/mul/ReadVariableOp ^batch_normalize/AssignMovingAvg/^batch_normalize/AssignMovingAvg/ReadVariableOp"^batch_normalize/AssignMovingAvg_11^batch_normalize/AssignMovingAvg_1/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2N
%batch_normalization_3/AssignMovingAvg%batch_normalization_3/AssignMovingAvg2l
4batch_normalization_3/AssignMovingAvg/ReadVariableOp4batch_normalization_3/AssignMovingAvg/ReadVariableOp2R
'batch_normalization_3/AssignMovingAvg_1'batch_normalization_3/AssignMovingAvg_12p
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2B
batch_normalize/AssignMovingAvgbatch_normalize/AssignMovingAvg2`
.batch_normalize/AssignMovingAvg/ReadVariableOp.batch_normalize/AssignMovingAvg/ReadVariableOp2F
!batch_normalize/AssignMovingAvg_1!batch_normalize/AssignMovingAvg_12d
0batch_normalize/AssignMovingAvg_1/ReadVariableOp0batch_normalize/AssignMovingAvg_1/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:L H
'
_output_shapes
:€€€€€€€€€

_user_specified_namesac:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	exports:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namedcc:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	net_dcd:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesjr:MI
'
_output_shapes
:€€€€€€€€€

_user_specified_nametide:NJ
'
_output_shapes
:€€€€€€€€€

_user_specified_namesmscg:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
Ы

ц
E__inference_dense_3_layer_call_and_return_conditional_losses_46413611

inputs0
matmul_readvariableop_resource:~-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:~*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
SigmoidSigmoidBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
IdentityIdentitySigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€~: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€~
 
_user_specified_nameinputs
µ
Ч
8__inference_batch_normalization_3_layer_call_fn_46413631

inputs/
!batchnorm_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:1
#batchnorm_readvariableop_1_resource:1
#batchnorm_readvariableop_2_resource:
identityИҐbatchnorm/ReadVariableOpҐbatchnorm/ReadVariableOp_1Ґbatchnorm/ReadVariableOp_2Ґbatchnorm/mul/ReadVariableOpv
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:w
batchnorm/addAddV2 batchnorm/ReadVariableOp:value:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
batchnorm/ReadVariableOp_1ReadVariableOp#batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0r
batchnorm/mul_2Mul"batchnorm/ReadVariableOp_1:value:0batchnorm/mul:z:0*
T0*
_output_shapes
:z
batchnorm/ReadVariableOp_2ReadVariableOp#batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0r
batchnorm/subSub"batchnorm/ReadVariableOp_2:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€Ї
NoOpNoOp^batchnorm/ReadVariableOp^batchnorm/ReadVariableOp_1^batchnorm/ReadVariableOp_2^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp28
batchnorm/ReadVariableOp_1batchnorm/ReadVariableOp_128
batchnorm/ReadVariableOp_2batchnorm/ReadVariableOp_22<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
жw
Л
*__inference_model_3_layer_call_fn_46411972
sac
exports
dcc
net_dcd
sjr
tide	
smscg
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:G
9batch_normalization_3_batchnorm_readvariableop_1_resource:G
9batch_normalization_3_batchnorm_readvariableop_2_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:A
3batch_normalize_batchnorm_readvariableop_1_resource:A
3batch_normalize_batchnorm_readvariableop_2_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ0batch_normalization_3/batchnorm/ReadVariableOp_1Ґ0batch_normalization_3/batchnorm/ReadVariableOp_2Ґ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ*batch_normalize/batchnorm/ReadVariableOp_1Ґ*batch_normalize/batchnorm/ReadVariableOp_2Ґ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    j
rescaling_3/mulMulsacrescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_18/subSubexportsnormalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_19/subSubdccnormalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_20/subSubnet_dcdnormalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_21/subSubsjrnormalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€k
normalization_22/subSubtidenormalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€l
normalization_23/subSubsmscgnormalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:є
#batch_normalization_3/batchnorm/addAddV26batch_normalization_3/batchnorm/ReadVariableOp:value:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€¶
0batch_normalization_3/batchnorm/ReadVariableOp_1ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0і
%batch_normalization_3/batchnorm/mul_2Mul8batch_normalization_3/batchnorm/ReadVariableOp_1:value:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:¶
0batch_normalization_3/batchnorm/ReadVariableOp_2ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0і
#batch_normalization_3/batchnorm/subSub8batch_normalization_3/batchnorm/ReadVariableOp_2:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:І
batch_normalize/batchnorm/addAddV20batch_normalize/batchnorm/ReadVariableOp:value:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ъ
*batch_normalize/batchnorm/ReadVariableOp_1ReadVariableOp3batch_normalize_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/mul_2Mul2batch_normalize/batchnorm/ReadVariableOp_1:value:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ъ
*batch_normalize/batchnorm/ReadVariableOp_2ReadVariableOp3batch_normalize_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/subSub2batch_normalize/batchnorm/ReadVariableOp_2:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€э
NoOpNoOp/^batch_normalization_3/batchnorm/ReadVariableOp1^batch_normalization_3/batchnorm/ReadVariableOp_11^batch_normalization_3/batchnorm/ReadVariableOp_23^batch_normalization_3/batchnorm/mul/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp+^batch_normalize/batchnorm/ReadVariableOp_1+^batch_normalize/batchnorm/ReadVariableOp_2-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2d
0batch_normalization_3/batchnorm/ReadVariableOp_10batch_normalization_3/batchnorm/ReadVariableOp_12d
0batch_normalization_3/batchnorm/ReadVariableOp_20batch_normalization_3/batchnorm/ReadVariableOp_22h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2X
*batch_normalize/batchnorm/ReadVariableOp_1*batch_normalize/batchnorm/ReadVariableOp_12X
*batch_normalize/batchnorm/ReadVariableOp_2*batch_normalize/batchnorm/ReadVariableOp_22\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:L H
'
_output_shapes
:€€€€€€€€€

_user_specified_namesac:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	exports:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namedcc:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	net_dcd:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesjr:MI
'
_output_shapes
:€€€€€€€€€

_user_specified_nametide:NJ
'
_output_shapes
:€€€€€€€€€

_user_specified_namesmscg:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
Ъ

х
D__inference_hidden_layer_call_and_return_conditional_losses_46413741

inputs0
matmul_readvariableop_resource:-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
SigmoidSigmoidBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
IdentityIdentitySigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
щ$
—
8__inference_batch_normalization_3_layer_call_fn_46413665

inputs5
'assignmovingavg_readvariableop_resource:7
)assignmovingavg_1_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:/
!batchnorm_readvariableop_resource:
identityИҐAssignMovingAvgҐAssignMovingAvg/ReadVariableOpҐAssignMovingAvg_1Ґ AssignMovingAvg_1/ReadVariableOpҐbatchnorm/ReadVariableOpҐbatchnorm/mul/ReadVariableOph
moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: 
moments/meanMeaninputs'moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(d
moments/StopGradientStopGradientmoments/mean:output:0*
T0*
_output_shapes

:З
moments/SquaredDifferenceSquaredDifferenceinputsmoments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€l
"moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ю
moments/varianceMeanmoments/SquaredDifference:z:0+moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(m
moments/SqueezeSqueezemoments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 s
moments/Squeeze_1Squeezemoments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Z
AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<В
AssignMovingAvg/ReadVariableOpReadVariableOp'assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0Б
AssignMovingAvg/subSub&AssignMovingAvg/ReadVariableOp:value:0moments/Squeeze:output:0*
T0*
_output_shapes
:x
AssignMovingAvg/mulMulAssignMovingAvg/sub:z:0AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:ђ
AssignMovingAvgAssignSubVariableOp'assignmovingavg_readvariableop_resourceAssignMovingAvg/mul:z:0^AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0\
AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ж
 AssignMovingAvg_1/ReadVariableOpReadVariableOp)assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0З
AssignMovingAvg_1/subSub(AssignMovingAvg_1/ReadVariableOp:value:0moments/Squeeze_1:output:0*
T0*
_output_shapes
:~
AssignMovingAvg_1/mulMulAssignMovingAvg_1/sub:z:0 AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:і
AssignMovingAvg_1AssignSubVariableOp)assignmovingavg_1_readvariableop_resourceAssignMovingAvg_1/mul:z:0!^AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:q
batchnorm/addAddV2moments/Squeeze_1:output:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€h
batchnorm/mul_2Mulmoments/Squeeze:output:0batchnorm/mul:z:0*
T0*
_output_shapes
:v
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0p
batchnorm/subSub batchnorm/ReadVariableOp:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€к
NoOpNoOp^AssignMovingAvg^AssignMovingAvg/ReadVariableOp^AssignMovingAvg_1!^AssignMovingAvg_1/ReadVariableOp^batchnorm/ReadVariableOp^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 2"
AssignMovingAvgAssignMovingAvg2@
AssignMovingAvg/ReadVariableOpAssignMovingAvg/ReadVariableOp2&
AssignMovingAvg_1AssignMovingAvg_12D
 AssignMovingAvg_1/ReadVariableOp AssignMovingAvg_1/ReadVariableOp24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp2<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
њ

љ
K__inference_concatenate_3_layer_call_and_return_conditional_losses_46413589
inputs_0
inputs_1
inputs_2
inputs_3
inputs_4
inputs_5
inputs_6
identityM
concat/axisConst*
_output_shapes
: *
dtype0*
value	B :©
concatConcatV2inputs_0inputs_1inputs_2inputs_3inputs_4inputs_5inputs_6concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~W
IdentityIdentityconcat:output:0*
T0*'
_output_shapes
:€€€€€€€€€~"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ъ
_input_shapesИ
Е:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:Q M
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/0:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/1:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/2:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/3:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/4:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/5:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/6
Ї©
ю
E__inference_model_3_layer_call_and_return_conditional_losses_46413549
inputs_0
inputs_1
inputs_2
inputs_3
inputs_4
inputs_5
inputs_6
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:K
=batch_normalization_3_assignmovingavg_readvariableop_resource:M
?batch_normalization_3_assignmovingavg_1_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:E
7batch_normalize_assignmovingavg_readvariableop_resource:G
9batch_normalize_assignmovingavg_1_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ%batch_normalization_3/AssignMovingAvgҐ4batch_normalization_3/AssignMovingAvg/ReadVariableOpҐ'batch_normalization_3/AssignMovingAvg_1Ґ6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐbatch_normalize/AssignMovingAvgҐ.batch_normalize/AssignMovingAvg/ReadVariableOpҐ!batch_normalize/AssignMovingAvg_1Ґ0batch_normalize/AssignMovingAvg_1/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    o
rescaling_3/mulMulinputs_0rescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_18/subSubinputs_1normalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_19/subSubinputs_2normalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_20/subSubinputs_3normalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_21/subSubinputs_4normalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_22/subSubinputs_5normalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_23/subSubinputs_6normalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
4batch_normalization_3/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Є
"batch_normalization_3/moments/meanMeandense_3/Sigmoid:y:0=batch_normalization_3/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Р
*batch_normalization_3/moments/StopGradientStopGradient+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes

:ј
/batch_normalization_3/moments/SquaredDifferenceSquaredDifferencedense_3/Sigmoid:y:03batch_normalization_3/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€В
8batch_normalization_3/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: а
&batch_normalization_3/moments/varianceMean3batch_normalization_3/moments/SquaredDifference:z:0Abatch_normalization_3/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Щ
%batch_normalization_3/moments/SqueezeSqueeze+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Я
'batch_normalization_3/moments/Squeeze_1Squeeze/batch_normalization_3/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 p
+batch_normalization_3/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ѓ
4batch_normalization_3/AssignMovingAvg/ReadVariableOpReadVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0√
)batch_normalization_3/AssignMovingAvg/subSub<batch_normalization_3/AssignMovingAvg/ReadVariableOp:value:0.batch_normalization_3/moments/Squeeze:output:0*
T0*
_output_shapes
:Ї
)batch_normalization_3/AssignMovingAvg/mulMul-batch_normalization_3/AssignMovingAvg/sub:z:04batch_normalization_3/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:Д
%batch_normalization_3/AssignMovingAvgAssignSubVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource-batch_normalization_3/AssignMovingAvg/mul:z:05^batch_normalization_3/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0r
-batch_normalization_3/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<≤
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpReadVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0…
+batch_normalization_3/AssignMovingAvg_1/subSub>batch_normalization_3/AssignMovingAvg_1/ReadVariableOp:value:00batch_normalization_3/moments/Squeeze_1:output:0*
T0*
_output_shapes
:ј
+batch_normalization_3/AssignMovingAvg_1/mulMul/batch_normalization_3/AssignMovingAvg_1/sub:z:06batch_normalization_3/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:М
'batch_normalization_3/AssignMovingAvg_1AssignSubVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource/batch_normalization_3/AssignMovingAvg_1/mul:z:07^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:≥
#batch_normalization_3/batchnorm/addAddV20batch_normalization_3/moments/Squeeze_1:output:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€™
%batch_normalization_3/batchnorm/mul_2Mul.batch_normalization_3/moments/Squeeze:output:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0≤
#batch_normalization_3/batchnorm/subSub6batch_normalization_3/batchnorm/ReadVariableOp:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€x
.batch_normalize/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ђ
batch_normalize/moments/meanMeanhidden/Sigmoid:y:07batch_normalize/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Д
$batch_normalize/moments/StopGradientStopGradient%batch_normalize/moments/mean:output:0*
T0*
_output_shapes

:≥
)batch_normalize/moments/SquaredDifferenceSquaredDifferencehidden/Sigmoid:y:0-batch_normalize/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€|
2batch_normalize/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: ќ
 batch_normalize/moments/varianceMean-batch_normalize/moments/SquaredDifference:z:0;batch_normalize/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Н
batch_normalize/moments/SqueezeSqueeze%batch_normalize/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 У
!batch_normalize/moments/Squeeze_1Squeeze)batch_normalize/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 j
%batch_normalize/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ґ
.batch_normalize/AssignMovingAvg/ReadVariableOpReadVariableOp7batch_normalize_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0±
#batch_normalize/AssignMovingAvg/subSub6batch_normalize/AssignMovingAvg/ReadVariableOp:value:0(batch_normalize/moments/Squeeze:output:0*
T0*
_output_shapes
:®
#batch_normalize/AssignMovingAvg/mulMul'batch_normalize/AssignMovingAvg/sub:z:0.batch_normalize/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:м
batch_normalize/AssignMovingAvgAssignSubVariableOp7batch_normalize_assignmovingavg_readvariableop_resource'batch_normalize/AssignMovingAvg/mul:z:0/^batch_normalize/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0l
'batch_normalize/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<¶
0batch_normalize/AssignMovingAvg_1/ReadVariableOpReadVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0Ј
%batch_normalize/AssignMovingAvg_1/subSub8batch_normalize/AssignMovingAvg_1/ReadVariableOp:value:0*batch_normalize/moments/Squeeze_1:output:0*
T0*
_output_shapes
:Ѓ
%batch_normalize/AssignMovingAvg_1/mulMul)batch_normalize/AssignMovingAvg_1/sub:z:00batch_normalize/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:ф
!batch_normalize/AssignMovingAvg_1AssignSubVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource)batch_normalize/AssignMovingAvg_1/mul:z:01^batch_normalize/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:°
batch_normalize/batchnorm/addAddV2*batch_normalize/moments/Squeeze_1:output:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ш
batch_normalize/batchnorm/mul_2Mul(batch_normalize/moments/Squeeze:output:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0†
batch_normalize/batchnorm/subSub0batch_normalize/batchnorm/ReadVariableOp:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€©
NoOpNoOp&^batch_normalization_3/AssignMovingAvg5^batch_normalization_3/AssignMovingAvg/ReadVariableOp(^batch_normalization_3/AssignMovingAvg_17^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp/^batch_normalization_3/batchnorm/ReadVariableOp3^batch_normalization_3/batchnorm/mul/ReadVariableOp ^batch_normalize/AssignMovingAvg/^batch_normalize/AssignMovingAvg/ReadVariableOp"^batch_normalize/AssignMovingAvg_11^batch_normalize/AssignMovingAvg_1/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2N
%batch_normalization_3/AssignMovingAvg%batch_normalization_3/AssignMovingAvg2l
4batch_normalization_3/AssignMovingAvg/ReadVariableOp4batch_normalization_3/AssignMovingAvg/ReadVariableOp2R
'batch_normalization_3/AssignMovingAvg_1'batch_normalization_3/AssignMovingAvg_12p
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2B
batch_normalize/AssignMovingAvgbatch_normalize/AssignMovingAvg2`
.batch_normalize/AssignMovingAvg/ReadVariableOp.batch_normalize/AssignMovingAvg/ReadVariableOp2F
!batch_normalize/AssignMovingAvg_1!batch_normalize/AssignMovingAvg_12d
0batch_normalize/AssignMovingAvg_1/ReadVariableOp0batch_normalize/AssignMovingAvg_1/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:Q M
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/0:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/1:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/2:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/3:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/4:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/5:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/6:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
€	
Џ
)__inference_hidden_layer_call_fn_46413730

inputs0
matmul_readvariableop_resource:-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
SigmoidSigmoidBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
IdentityIdentitySigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
ЕИ
ƒ
#__inference__wrapped_model_46411640
sac
exports
dcc
net_dcd
sjr
tide	
smscg"
model_3_normalization_18_sub_y#
model_3_normalization_18_sqrt_x"
model_3_normalization_19_sub_y#
model_3_normalization_19_sqrt_x"
model_3_normalization_20_sub_y#
model_3_normalization_20_sqrt_x"
model_3_normalization_21_sub_y#
model_3_normalization_21_sqrt_x"
model_3_normalization_22_sub_y#
model_3_normalization_22_sqrt_x"
model_3_normalization_23_sub_y#
model_3_normalization_23_sqrt_x@
.model_3_dense_3_matmul_readvariableop_resource:~=
/model_3_dense_3_biasadd_readvariableop_resource:M
?model_3_batch_normalization_3_batchnorm_readvariableop_resource:Q
Cmodel_3_batch_normalization_3_batchnorm_mul_readvariableop_resource:O
Amodel_3_batch_normalization_3_batchnorm_readvariableop_1_resource:O
Amodel_3_batch_normalization_3_batchnorm_readvariableop_2_resource:?
-model_3_hidden_matmul_readvariableop_resource:<
.model_3_hidden_biasadd_readvariableop_resource:G
9model_3_batch_normalize_batchnorm_readvariableop_resource:K
=model_3_batch_normalize_batchnorm_mul_readvariableop_resource:I
;model_3_batch_normalize_batchnorm_readvariableop_1_resource:I
;model_3_batch_normalize_batchnorm_readvariableop_2_resource:;
)model_3_ec_matmul_readvariableop_resource:8
*model_3_ec_biasadd_readvariableop_resource:
identityИҐ6model_3/batch_normalization_3/batchnorm/ReadVariableOpҐ8model_3/batch_normalization_3/batchnorm/ReadVariableOp_1Ґ8model_3/batch_normalization_3/batchnorm/ReadVariableOp_2Ґ:model_3/batch_normalization_3/batchnorm/mul/ReadVariableOpҐ0model_3/batch_normalize/batchnorm/ReadVariableOpҐ2model_3/batch_normalize/batchnorm/ReadVariableOp_1Ґ2model_3/batch_normalize/batchnorm/ReadVariableOp_2Ґ4model_3/batch_normalize/batchnorm/mul/ReadVariableOpҐ&model_3/dense_3/BiasAdd/ReadVariableOpҐ%model_3/dense_3/MatMul/ReadVariableOpҐ!model_3/ec/BiasAdd/ReadVariableOpҐ model_3/ec/MatMul/ReadVariableOpҐ%model_3/hidden/BiasAdd/ReadVariableOpҐ$model_3/hidden/MatMul/ReadVariableOp_
model_3/rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7a
model_3/rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    z
model_3/rescaling_3/mulMulsac#model_3/rescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ц
model_3/rescaling_3/addAddV2model_3/rescaling_3/mul:z:0%model_3/rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
model_3/normalization_18/subSubexportsmodel_3_normalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€o
model_3/normalization_18/SqrtSqrtmodel_3_normalization_18_sqrt_x*
T0*
_output_shapes

:g
"model_3/normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3§
 model_3/normalization_18/MaximumMaximum!model_3/normalization_18/Sqrt:y:0+model_3/normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:•
 model_3/normalization_18/truedivRealDiv model_3/normalization_18/sub:z:0$model_3/normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
model_3/normalization_19/subSubdccmodel_3_normalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€o
model_3/normalization_19/SqrtSqrtmodel_3_normalization_19_sqrt_x*
T0*
_output_shapes

:g
"model_3/normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3§
 model_3/normalization_19/MaximumMaximum!model_3/normalization_19/Sqrt:y:0+model_3/normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:•
 model_3/normalization_19/truedivRealDiv model_3/normalization_19/sub:z:0$model_3/normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€~
model_3/normalization_20/subSubnet_dcdmodel_3_normalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€o
model_3/normalization_20/SqrtSqrtmodel_3_normalization_20_sqrt_x*
T0*
_output_shapes

:g
"model_3/normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3§
 model_3/normalization_20/MaximumMaximum!model_3/normalization_20/Sqrt:y:0+model_3/normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:•
 model_3/normalization_20/truedivRealDiv model_3/normalization_20/sub:z:0$model_3/normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
model_3/normalization_21/subSubsjrmodel_3_normalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€o
model_3/normalization_21/SqrtSqrtmodel_3_normalization_21_sqrt_x*
T0*
_output_shapes

:g
"model_3/normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3§
 model_3/normalization_21/MaximumMaximum!model_3/normalization_21/Sqrt:y:0+model_3/normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:•
 model_3/normalization_21/truedivRealDiv model_3/normalization_21/sub:z:0$model_3/normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€{
model_3/normalization_22/subSubtidemodel_3_normalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€o
model_3/normalization_22/SqrtSqrtmodel_3_normalization_22_sqrt_x*
T0*
_output_shapes

:g
"model_3/normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3§
 model_3/normalization_22/MaximumMaximum!model_3/normalization_22/Sqrt:y:0+model_3/normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:•
 model_3/normalization_22/truedivRealDiv model_3/normalization_22/sub:z:0$model_3/normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€|
model_3/normalization_23/subSubsmscgmodel_3_normalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€o
model_3/normalization_23/SqrtSqrtmodel_3_normalization_23_sqrt_x*
T0*
_output_shapes

:g
"model_3/normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3§
 model_3/normalization_23/MaximumMaximum!model_3/normalization_23/Sqrt:y:0+model_3/normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:•
 model_3/normalization_23/truedivRealDiv model_3/normalization_23/sub:z:0$model_3/normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€c
!model_3/concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :Р
model_3/concatenate_3/concatConcatV2model_3/rescaling_3/add:z:0$model_3/normalization_18/truediv:z:0$model_3/normalization_19/truediv:z:0$model_3/normalization_20/truediv:z:0$model_3/normalization_21/truediv:z:0$model_3/normalization_22/truediv:z:0$model_3/normalization_23/truediv:z:0*model_3/concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Ф
%model_3/dense_3/MatMul/ReadVariableOpReadVariableOp.model_3_dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0®
model_3/dense_3/MatMulMatMul%model_3/concatenate_3/concat:output:0-model_3/dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€Т
&model_3/dense_3/BiasAdd/ReadVariableOpReadVariableOp/model_3_dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0¶
model_3/dense_3/BiasAddBiasAdd model_3/dense_3/MatMul:product:0.model_3/dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€v
model_3/dense_3/SigmoidSigmoid model_3/dense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€≤
6model_3/batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp?model_3_batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0r
-model_3/batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:—
+model_3/batch_normalization_3/batchnorm/addAddV2>model_3/batch_normalization_3/batchnorm/ReadVariableOp:value:06model_3/batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:М
-model_3/batch_normalization_3/batchnorm/RsqrtRsqrt/model_3/batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:Ї
:model_3/batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOpCmodel_3_batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ќ
+model_3/batch_normalization_3/batchnorm/mulMul1model_3/batch_normalization_3/batchnorm/Rsqrt:y:0Bmodel_3/batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:і
-model_3/batch_normalization_3/batchnorm/mul_1Mulmodel_3/dense_3/Sigmoid:y:0/model_3/batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€ґ
8model_3/batch_normalization_3/batchnorm/ReadVariableOp_1ReadVariableOpAmodel_3_batch_normalization_3_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0ћ
-model_3/batch_normalization_3/batchnorm/mul_2Mul@model_3/batch_normalization_3/batchnorm/ReadVariableOp_1:value:0/model_3/batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:ґ
8model_3/batch_normalization_3/batchnorm/ReadVariableOp_2ReadVariableOpAmodel_3_batch_normalization_3_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0ћ
+model_3/batch_normalization_3/batchnorm/subSub@model_3/batch_normalization_3/batchnorm/ReadVariableOp_2:value:01model_3/batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:ћ
-model_3/batch_normalization_3/batchnorm/add_1AddV21model_3/batch_normalization_3/batchnorm/mul_1:z:0/model_3/batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€Т
$model_3/hidden/MatMul/ReadVariableOpReadVariableOp-model_3_hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0≤
model_3/hidden/MatMulMatMul1model_3/batch_normalization_3/batchnorm/add_1:z:0,model_3/hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€Р
%model_3/hidden/BiasAdd/ReadVariableOpReadVariableOp.model_3_hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0£
model_3/hidden/BiasAddBiasAddmodel_3/hidden/MatMul:product:0-model_3/hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€t
model_3/hidden/SigmoidSigmoidmodel_3/hidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€¶
0model_3/batch_normalize/batchnorm/ReadVariableOpReadVariableOp9model_3_batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0l
'model_3/batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:њ
%model_3/batch_normalize/batchnorm/addAddV28model_3/batch_normalize/batchnorm/ReadVariableOp:value:00model_3/batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:А
'model_3/batch_normalize/batchnorm/RsqrtRsqrt)model_3/batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ѓ
4model_3/batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp=model_3_batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0Љ
%model_3/batch_normalize/batchnorm/mulMul+model_3/batch_normalize/batchnorm/Rsqrt:y:0<model_3/batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:І
'model_3/batch_normalize/batchnorm/mul_1Mulmodel_3/hidden/Sigmoid:y:0)model_3/batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€™
2model_3/batch_normalize/batchnorm/ReadVariableOp_1ReadVariableOp;model_3_batch_normalize_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0Ї
'model_3/batch_normalize/batchnorm/mul_2Mul:model_3/batch_normalize/batchnorm/ReadVariableOp_1:value:0)model_3/batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:™
2model_3/batch_normalize/batchnorm/ReadVariableOp_2ReadVariableOp;model_3_batch_normalize_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0Ї
%model_3/batch_normalize/batchnorm/subSub:model_3/batch_normalize/batchnorm/ReadVariableOp_2:value:0+model_3/batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ї
'model_3/batch_normalize/batchnorm/add_1AddV2+model_3/batch_normalize/batchnorm/mul_1:z:0)model_3/batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€К
 model_3/ec/MatMul/ReadVariableOpReadVariableOp)model_3_ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0§
model_3/ec/MatMulMatMul+model_3/batch_normalize/batchnorm/add_1:z:0(model_3/ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€И
!model_3/ec/BiasAdd/ReadVariableOpReadVariableOp*model_3_ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Ч
model_3/ec/BiasAddBiasAddmodel_3/ec/MatMul:product:0)model_3/ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
model_3/ec/ReluRelumodel_3/ec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€l
IdentityIdentitymodel_3/ec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€н
NoOpNoOp7^model_3/batch_normalization_3/batchnorm/ReadVariableOp9^model_3/batch_normalization_3/batchnorm/ReadVariableOp_19^model_3/batch_normalization_3/batchnorm/ReadVariableOp_2;^model_3/batch_normalization_3/batchnorm/mul/ReadVariableOp1^model_3/batch_normalize/batchnorm/ReadVariableOp3^model_3/batch_normalize/batchnorm/ReadVariableOp_13^model_3/batch_normalize/batchnorm/ReadVariableOp_25^model_3/batch_normalize/batchnorm/mul/ReadVariableOp'^model_3/dense_3/BiasAdd/ReadVariableOp&^model_3/dense_3/MatMul/ReadVariableOp"^model_3/ec/BiasAdd/ReadVariableOp!^model_3/ec/MatMul/ReadVariableOp&^model_3/hidden/BiasAdd/ReadVariableOp%^model_3/hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2p
6model_3/batch_normalization_3/batchnorm/ReadVariableOp6model_3/batch_normalization_3/batchnorm/ReadVariableOp2t
8model_3/batch_normalization_3/batchnorm/ReadVariableOp_18model_3/batch_normalization_3/batchnorm/ReadVariableOp_12t
8model_3/batch_normalization_3/batchnorm/ReadVariableOp_28model_3/batch_normalization_3/batchnorm/ReadVariableOp_22x
:model_3/batch_normalization_3/batchnorm/mul/ReadVariableOp:model_3/batch_normalization_3/batchnorm/mul/ReadVariableOp2d
0model_3/batch_normalize/batchnorm/ReadVariableOp0model_3/batch_normalize/batchnorm/ReadVariableOp2h
2model_3/batch_normalize/batchnorm/ReadVariableOp_12model_3/batch_normalize/batchnorm/ReadVariableOp_12h
2model_3/batch_normalize/batchnorm/ReadVariableOp_22model_3/batch_normalize/batchnorm/ReadVariableOp_22l
4model_3/batch_normalize/batchnorm/mul/ReadVariableOp4model_3/batch_normalize/batchnorm/mul/ReadVariableOp2P
&model_3/dense_3/BiasAdd/ReadVariableOp&model_3/dense_3/BiasAdd/ReadVariableOp2N
%model_3/dense_3/MatMul/ReadVariableOp%model_3/dense_3/MatMul/ReadVariableOp2F
!model_3/ec/BiasAdd/ReadVariableOp!model_3/ec/BiasAdd/ReadVariableOp2D
 model_3/ec/MatMul/ReadVariableOp model_3/ec/MatMul/ReadVariableOp2N
%model_3/hidden/BiasAdd/ReadVariableOp%model_3/hidden/BiasAdd/ReadVariableOp2L
$model_3/hidden/MatMul/ReadVariableOp$model_3/hidden/MatMul/ReadVariableOp:L H
'
_output_shapes
:€€€€€€€€€

_user_specified_namesac:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	exports:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namedcc:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	net_dcd:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesjr:MI
'
_output_shapes
:€€€€€€€€€

_user_specified_nametide:NJ
'
_output_shapes
:€€€€€€€€€

_user_specified_namesmscg:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
…x
Њ
E__inference_model_3_layer_call_and_return_conditional_losses_46413410
inputs_0
inputs_1
inputs_2
inputs_3
inputs_4
inputs_5
inputs_6
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:G
9batch_normalization_3_batchnorm_readvariableop_1_resource:G
9batch_normalization_3_batchnorm_readvariableop_2_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:A
3batch_normalize_batchnorm_readvariableop_1_resource:A
3batch_normalize_batchnorm_readvariableop_2_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ0batch_normalization_3/batchnorm/ReadVariableOp_1Ґ0batch_normalization_3/batchnorm/ReadVariableOp_2Ґ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ*batch_normalize/batchnorm/ReadVariableOp_1Ґ*batch_normalize/batchnorm/ReadVariableOp_2Ґ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    o
rescaling_3/mulMulinputs_0rescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_18/subSubinputs_1normalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_19/subSubinputs_2normalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_20/subSubinputs_3normalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_21/subSubinputs_4normalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_22/subSubinputs_5normalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_23/subSubinputs_6normalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:є
#batch_normalization_3/batchnorm/addAddV26batch_normalization_3/batchnorm/ReadVariableOp:value:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€¶
0batch_normalization_3/batchnorm/ReadVariableOp_1ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0і
%batch_normalization_3/batchnorm/mul_2Mul8batch_normalization_3/batchnorm/ReadVariableOp_1:value:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:¶
0batch_normalization_3/batchnorm/ReadVariableOp_2ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0і
#batch_normalization_3/batchnorm/subSub8batch_normalization_3/batchnorm/ReadVariableOp_2:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:І
batch_normalize/batchnorm/addAddV20batch_normalize/batchnorm/ReadVariableOp:value:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ъ
*batch_normalize/batchnorm/ReadVariableOp_1ReadVariableOp3batch_normalize_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/mul_2Mul2batch_normalize/batchnorm/ReadVariableOp_1:value:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ъ
*batch_normalize/batchnorm/ReadVariableOp_2ReadVariableOp3batch_normalize_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/subSub2batch_normalize/batchnorm/ReadVariableOp_2:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€э
NoOpNoOp/^batch_normalization_3/batchnorm/ReadVariableOp1^batch_normalization_3/batchnorm/ReadVariableOp_11^batch_normalization_3/batchnorm/ReadVariableOp_23^batch_normalization_3/batchnorm/mul/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp+^batch_normalize/batchnorm/ReadVariableOp_1+^batch_normalize/batchnorm/ReadVariableOp_2-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2d
0batch_normalization_3/batchnorm/ReadVariableOp_10batch_normalization_3/batchnorm/ReadVariableOp_12d
0batch_normalization_3/batchnorm/ReadVariableOp_20batch_normalization_3/batchnorm/ReadVariableOp_22h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2X
*batch_normalize/batchnorm/ReadVariableOp_1*batch_normalize/batchnorm/ReadVariableOp_12X
*batch_normalize/batchnorm/ReadVariableOp_2*batch_normalize/batchnorm/ReadVariableOp_22\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:Q M
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/0:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/1:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/2:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/3:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/4:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/5:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/6:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
т®
ж
E__inference_model_3_layer_call_and_return_conditional_losses_46412978
sac
exports
dcc
net_dcd
sjr
tide	
smscg
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:K
=batch_normalization_3_assignmovingavg_readvariableop_resource:M
?batch_normalization_3_assignmovingavg_1_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:E
7batch_normalize_assignmovingavg_readvariableop_resource:G
9batch_normalize_assignmovingavg_1_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ%batch_normalization_3/AssignMovingAvgҐ4batch_normalization_3/AssignMovingAvg/ReadVariableOpҐ'batch_normalization_3/AssignMovingAvg_1Ґ6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐbatch_normalize/AssignMovingAvgҐ.batch_normalize/AssignMovingAvg/ReadVariableOpҐ!batch_normalize/AssignMovingAvg_1Ґ0batch_normalize/AssignMovingAvg_1/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    j
rescaling_3/mulMulsacrescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_18/subSubexportsnormalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_19/subSubdccnormalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_20/subSubnet_dcdnormalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_21/subSubsjrnormalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€k
normalization_22/subSubtidenormalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€l
normalization_23/subSubsmscgnormalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
4batch_normalization_3/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Є
"batch_normalization_3/moments/meanMeandense_3/Sigmoid:y:0=batch_normalization_3/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Р
*batch_normalization_3/moments/StopGradientStopGradient+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes

:ј
/batch_normalization_3/moments/SquaredDifferenceSquaredDifferencedense_3/Sigmoid:y:03batch_normalization_3/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€В
8batch_normalization_3/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: а
&batch_normalization_3/moments/varianceMean3batch_normalization_3/moments/SquaredDifference:z:0Abatch_normalization_3/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Щ
%batch_normalization_3/moments/SqueezeSqueeze+batch_normalization_3/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Я
'batch_normalization_3/moments/Squeeze_1Squeeze/batch_normalization_3/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 p
+batch_normalization_3/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ѓ
4batch_normalization_3/AssignMovingAvg/ReadVariableOpReadVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0√
)batch_normalization_3/AssignMovingAvg/subSub<batch_normalization_3/AssignMovingAvg/ReadVariableOp:value:0.batch_normalization_3/moments/Squeeze:output:0*
T0*
_output_shapes
:Ї
)batch_normalization_3/AssignMovingAvg/mulMul-batch_normalization_3/AssignMovingAvg/sub:z:04batch_normalization_3/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:Д
%batch_normalization_3/AssignMovingAvgAssignSubVariableOp=batch_normalization_3_assignmovingavg_readvariableop_resource-batch_normalization_3/AssignMovingAvg/mul:z:05^batch_normalization_3/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0r
-batch_normalization_3/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<≤
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOpReadVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0…
+batch_normalization_3/AssignMovingAvg_1/subSub>batch_normalization_3/AssignMovingAvg_1/ReadVariableOp:value:00batch_normalization_3/moments/Squeeze_1:output:0*
T0*
_output_shapes
:ј
+batch_normalization_3/AssignMovingAvg_1/mulMul/batch_normalization_3/AssignMovingAvg_1/sub:z:06batch_normalization_3/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:М
'batch_normalization_3/AssignMovingAvg_1AssignSubVariableOp?batch_normalization_3_assignmovingavg_1_readvariableop_resource/batch_normalization_3/AssignMovingAvg_1/mul:z:07^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:≥
#batch_normalization_3/batchnorm/addAddV20batch_normalization_3/moments/Squeeze_1:output:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€™
%batch_normalization_3/batchnorm/mul_2Mul.batch_normalization_3/moments/Squeeze:output:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0≤
#batch_normalization_3/batchnorm/subSub6batch_normalization_3/batchnorm/ReadVariableOp:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€x
.batch_normalize/moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ђ
batch_normalize/moments/meanMeanhidden/Sigmoid:y:07batch_normalize/moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Д
$batch_normalize/moments/StopGradientStopGradient%batch_normalize/moments/mean:output:0*
T0*
_output_shapes

:≥
)batch_normalize/moments/SquaredDifferenceSquaredDifferencehidden/Sigmoid:y:0-batch_normalize/moments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€|
2batch_normalize/moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: ќ
 batch_normalize/moments/varianceMean-batch_normalize/moments/SquaredDifference:z:0;batch_normalize/moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(Н
batch_normalize/moments/SqueezeSqueeze%batch_normalize/moments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 У
!batch_normalize/moments/Squeeze_1Squeeze)batch_normalize/moments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 j
%batch_normalize/AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ґ
.batch_normalize/AssignMovingAvg/ReadVariableOpReadVariableOp7batch_normalize_assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0±
#batch_normalize/AssignMovingAvg/subSub6batch_normalize/AssignMovingAvg/ReadVariableOp:value:0(batch_normalize/moments/Squeeze:output:0*
T0*
_output_shapes
:®
#batch_normalize/AssignMovingAvg/mulMul'batch_normalize/AssignMovingAvg/sub:z:0.batch_normalize/AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:м
batch_normalize/AssignMovingAvgAssignSubVariableOp7batch_normalize_assignmovingavg_readvariableop_resource'batch_normalize/AssignMovingAvg/mul:z:0/^batch_normalize/AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0l
'batch_normalize/AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<¶
0batch_normalize/AssignMovingAvg_1/ReadVariableOpReadVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0Ј
%batch_normalize/AssignMovingAvg_1/subSub8batch_normalize/AssignMovingAvg_1/ReadVariableOp:value:0*batch_normalize/moments/Squeeze_1:output:0*
T0*
_output_shapes
:Ѓ
%batch_normalize/AssignMovingAvg_1/mulMul)batch_normalize/AssignMovingAvg_1/sub:z:00batch_normalize/AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:ф
!batch_normalize/AssignMovingAvg_1AssignSubVariableOp9batch_normalize_assignmovingavg_1_readvariableop_resource)batch_normalize/AssignMovingAvg_1/mul:z:01^batch_normalize/AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:°
batch_normalize/batchnorm/addAddV2*batch_normalize/moments/Squeeze_1:output:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ш
batch_normalize/batchnorm/mul_2Mul(batch_normalize/moments/Squeeze:output:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0†
batch_normalize/batchnorm/subSub0batch_normalize/batchnorm/ReadVariableOp:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€©
NoOpNoOp&^batch_normalization_3/AssignMovingAvg5^batch_normalization_3/AssignMovingAvg/ReadVariableOp(^batch_normalization_3/AssignMovingAvg_17^batch_normalization_3/AssignMovingAvg_1/ReadVariableOp/^batch_normalization_3/batchnorm/ReadVariableOp3^batch_normalization_3/batchnorm/mul/ReadVariableOp ^batch_normalize/AssignMovingAvg/^batch_normalize/AssignMovingAvg/ReadVariableOp"^batch_normalize/AssignMovingAvg_11^batch_normalize/AssignMovingAvg_1/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2N
%batch_normalization_3/AssignMovingAvg%batch_normalization_3/AssignMovingAvg2l
4batch_normalization_3/AssignMovingAvg/ReadVariableOp4batch_normalization_3/AssignMovingAvg/ReadVariableOp2R
'batch_normalization_3/AssignMovingAvg_1'batch_normalization_3/AssignMovingAvg_12p
6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp6batch_normalization_3/AssignMovingAvg_1/ReadVariableOp2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2B
batch_normalize/AssignMovingAvgbatch_normalize/AssignMovingAvg2`
.batch_normalize/AssignMovingAvg/ReadVariableOp.batch_normalize/AssignMovingAvg/ReadVariableOp2F
!batch_normalize/AssignMovingAvg_1!batch_normalize/AssignMovingAvg_12d
0batch_normalize/AssignMovingAvg_1/ReadVariableOp0batch_normalize/AssignMovingAvg_1/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:L H
'
_output_shapes
:€€€€€€€€€

_user_specified_namesac:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	exports:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namedcc:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	net_dcd:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesjr:MI
'
_output_shapes
:€€€€€€€€€

_user_specified_nametide:NJ
'
_output_shapes
:€€€€€€€€€

_user_specified_namesmscg:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
Ф%
м
S__inference_batch_normalization_3_layer_call_and_return_conditional_losses_46413719

inputs5
'assignmovingavg_readvariableop_resource:7
)assignmovingavg_1_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:/
!batchnorm_readvariableop_resource:
identityИҐAssignMovingAvgҐAssignMovingAvg/ReadVariableOpҐAssignMovingAvg_1Ґ AssignMovingAvg_1/ReadVariableOpҐbatchnorm/ReadVariableOpҐbatchnorm/mul/ReadVariableOph
moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: 
moments/meanMeaninputs'moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(d
moments/StopGradientStopGradientmoments/mean:output:0*
T0*
_output_shapes

:З
moments/SquaredDifferenceSquaredDifferenceinputsmoments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€l
"moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ю
moments/varianceMeanmoments/SquaredDifference:z:0+moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(m
moments/SqueezeSqueezemoments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 s
moments/Squeeze_1Squeezemoments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Z
AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<В
AssignMovingAvg/ReadVariableOpReadVariableOp'assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0Б
AssignMovingAvg/subSub&AssignMovingAvg/ReadVariableOp:value:0moments/Squeeze:output:0*
T0*
_output_shapes
:x
AssignMovingAvg/mulMulAssignMovingAvg/sub:z:0AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:ђ
AssignMovingAvgAssignSubVariableOp'assignmovingavg_readvariableop_resourceAssignMovingAvg/mul:z:0^AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0\
AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ж
 AssignMovingAvg_1/ReadVariableOpReadVariableOp)assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0З
AssignMovingAvg_1/subSub(AssignMovingAvg_1/ReadVariableOp:value:0moments/Squeeze_1:output:0*
T0*
_output_shapes
:~
AssignMovingAvg_1/mulMulAssignMovingAvg_1/sub:z:0 AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:і
AssignMovingAvg_1AssignSubVariableOp)assignmovingavg_1_readvariableop_resourceAssignMovingAvg_1/mul:z:0!^AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:q
batchnorm/addAddV2moments/Squeeze_1:output:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€h
batchnorm/mul_2Mulmoments/Squeeze:output:0batchnorm/mul:z:0*
T0*
_output_shapes
:v
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0p
batchnorm/subSub batchnorm/ReadVariableOp:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€к
NoOpNoOp^AssignMovingAvg^AssignMovingAvg/ReadVariableOp^AssignMovingAvg_1!^AssignMovingAvg_1/ReadVariableOp^batchnorm/ReadVariableOp^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 2"
AssignMovingAvgAssignMovingAvg2@
AssignMovingAvg/ReadVariableOpAssignMovingAvg/ReadVariableOp2&
AssignMovingAvg_1AssignMovingAvg_12D
 AssignMovingAvg_1/ReadVariableOp AssignMovingAvg_1/ReadVariableOp24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp2<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
ѓ
С
2__inference_batch_normalize_layer_call_fn_46413761

inputs/
!batchnorm_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:1
#batchnorm_readvariableop_1_resource:1
#batchnorm_readvariableop_2_resource:
identityИҐbatchnorm/ReadVariableOpҐbatchnorm/ReadVariableOp_1Ґbatchnorm/ReadVariableOp_2Ґbatchnorm/mul/ReadVariableOpv
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:w
batchnorm/addAddV2 batchnorm/ReadVariableOp:value:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
batchnorm/ReadVariableOp_1ReadVariableOp#batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0r
batchnorm/mul_2Mul"batchnorm/ReadVariableOp_1:value:0batchnorm/mul:z:0*
T0*
_output_shapes
:z
batchnorm/ReadVariableOp_2ReadVariableOp#batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0r
batchnorm/subSub"batchnorm/ReadVariableOp_2:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€Ї
NoOpNoOp^batchnorm/ReadVariableOp^batchnorm/ReadVariableOp_1^batchnorm/ReadVariableOp_2^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp28
batchnorm/ReadVariableOp_1batchnorm/ReadVariableOp_128
batchnorm/ReadVariableOp_2batchnorm/ReadVariableOp_22<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Ѓx
£
*__inference_model_3_layer_call_fn_46413160
inputs_0
inputs_1
inputs_2
inputs_3
inputs_4
inputs_5
inputs_6
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:G
9batch_normalization_3_batchnorm_readvariableop_1_resource:G
9batch_normalization_3_batchnorm_readvariableop_2_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:A
3batch_normalize_batchnorm_readvariableop_1_resource:A
3batch_normalize_batchnorm_readvariableop_2_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ0batch_normalization_3/batchnorm/ReadVariableOp_1Ґ0batch_normalization_3/batchnorm/ReadVariableOp_2Ґ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ*batch_normalize/batchnorm/ReadVariableOp_1Ґ*batch_normalize/batchnorm/ReadVariableOp_2Ґ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    o
rescaling_3/mulMulinputs_0rescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_18/subSubinputs_1normalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_19/subSubinputs_2normalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_20/subSubinputs_3normalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_21/subSubinputs_4normalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_22/subSubinputs_5normalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€o
normalization_23/subSubinputs_6normalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:є
#batch_normalization_3/batchnorm/addAddV26batch_normalization_3/batchnorm/ReadVariableOp:value:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€¶
0batch_normalization_3/batchnorm/ReadVariableOp_1ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0і
%batch_normalization_3/batchnorm/mul_2Mul8batch_normalization_3/batchnorm/ReadVariableOp_1:value:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:¶
0batch_normalization_3/batchnorm/ReadVariableOp_2ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0і
#batch_normalization_3/batchnorm/subSub8batch_normalization_3/batchnorm/ReadVariableOp_2:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:І
batch_normalize/batchnorm/addAddV20batch_normalize/batchnorm/ReadVariableOp:value:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ъ
*batch_normalize/batchnorm/ReadVariableOp_1ReadVariableOp3batch_normalize_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/mul_2Mul2batch_normalize/batchnorm/ReadVariableOp_1:value:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ъ
*batch_normalize/batchnorm/ReadVariableOp_2ReadVariableOp3batch_normalize_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/subSub2batch_normalize/batchnorm/ReadVariableOp_2:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€э
NoOpNoOp/^batch_normalization_3/batchnorm/ReadVariableOp1^batch_normalization_3/batchnorm/ReadVariableOp_11^batch_normalization_3/batchnorm/ReadVariableOp_23^batch_normalization_3/batchnorm/mul/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp+^batch_normalize/batchnorm/ReadVariableOp_1+^batch_normalize/batchnorm/ReadVariableOp_2-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2d
0batch_normalization_3/batchnorm/ReadVariableOp_10batch_normalization_3/batchnorm/ReadVariableOp_12d
0batch_normalization_3/batchnorm/ReadVariableOp_20batch_normalization_3/batchnorm/ReadVariableOp_22h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2X
*batch_normalize/batchnorm/ReadVariableOp_1*batch_normalize/batchnorm/ReadVariableOp_12X
*batch_normalize/batchnorm/ReadVariableOp_2*batch_normalize/batchnorm/ReadVariableOp_22\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:Q M
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/0:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/1:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/2:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/3:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/4:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/5:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/6:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
ь	
÷
%__inference_ec_layer_call_fn_46413860

inputs0
matmul_readvariableop_resource:-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€P
ReluReluBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€a
IdentityIdentityRelu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Ч

с
@__inference_ec_layer_call_and_return_conditional_losses_46413871

inputs0
matmul_readvariableop_resource:-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€P
ReluReluBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€a
IdentityIdentityRelu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
О%
ж
M__inference_batch_normalize_layer_call_and_return_conditional_losses_46413849

inputs5
'assignmovingavg_readvariableop_resource:7
)assignmovingavg_1_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:/
!batchnorm_readvariableop_resource:
identityИҐAssignMovingAvgҐAssignMovingAvg/ReadVariableOpҐAssignMovingAvg_1Ґ AssignMovingAvg_1/ReadVariableOpҐbatchnorm/ReadVariableOpҐbatchnorm/mul/ReadVariableOph
moments/mean/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: 
moments/meanMeaninputs'moments/mean/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(d
moments/StopGradientStopGradientmoments/mean:output:0*
T0*
_output_shapes

:З
moments/SquaredDifferenceSquaredDifferenceinputsmoments/StopGradient:output:0*
T0*'
_output_shapes
:€€€€€€€€€l
"moments/variance/reduction_indicesConst*
_output_shapes
:*
dtype0*
valueB: Ю
moments/varianceMeanmoments/SquaredDifference:z:0+moments/variance/reduction_indices:output:0*
T0*
_output_shapes

:*
	keep_dims(m
moments/SqueezeSqueezemoments/mean:output:0*
T0*
_output_shapes
:*
squeeze_dims
 s
moments/Squeeze_1Squeezemoments/variance:output:0*
T0*
_output_shapes
:*
squeeze_dims
 Z
AssignMovingAvg/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<В
AssignMovingAvg/ReadVariableOpReadVariableOp'assignmovingavg_readvariableop_resource*
_output_shapes
:*
dtype0Б
AssignMovingAvg/subSub&AssignMovingAvg/ReadVariableOp:value:0moments/Squeeze:output:0*
T0*
_output_shapes
:x
AssignMovingAvg/mulMulAssignMovingAvg/sub:z:0AssignMovingAvg/decay:output:0*
T0*
_output_shapes
:ђ
AssignMovingAvgAssignSubVariableOp'assignmovingavg_readvariableop_resourceAssignMovingAvg/mul:z:0^AssignMovingAvg/ReadVariableOp*
_output_shapes
 *
dtype0\
AssignMovingAvg_1/decayConst*
_output_shapes
: *
dtype0*
valueB
 *
„#<Ж
 AssignMovingAvg_1/ReadVariableOpReadVariableOp)assignmovingavg_1_readvariableop_resource*
_output_shapes
:*
dtype0З
AssignMovingAvg_1/subSub(AssignMovingAvg_1/ReadVariableOp:value:0moments/Squeeze_1:output:0*
T0*
_output_shapes
:~
AssignMovingAvg_1/mulMulAssignMovingAvg_1/sub:z:0 AssignMovingAvg_1/decay:output:0*
T0*
_output_shapes
:і
AssignMovingAvg_1AssignSubVariableOp)assignmovingavg_1_readvariableop_resourceAssignMovingAvg_1/mul:z:0!^AssignMovingAvg_1/ReadVariableOp*
_output_shapes
 *
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:q
batchnorm/addAddV2moments/Squeeze_1:output:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€h
batchnorm/mul_2Mulmoments/Squeeze:output:0batchnorm/mul:z:0*
T0*
_output_shapes
:v
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0p
batchnorm/subSub batchnorm/ReadVariableOp:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€к
NoOpNoOp^AssignMovingAvg^AssignMovingAvg/ReadVariableOp^AssignMovingAvg_1!^AssignMovingAvg_1/ReadVariableOp^batchnorm/ReadVariableOp^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 2"
AssignMovingAvgAssignMovingAvg2@
AssignMovingAvg/ReadVariableOpAssignMovingAvg/ReadVariableOp2&
AssignMovingAvg_1AssignMovingAvg_12D
 AssignMovingAvg_1/ReadVariableOp AssignMovingAvg_1/ReadVariableOp24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp2<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
 
ђ
M__inference_batch_normalize_layer_call_and_return_conditional_losses_46413815

inputs/
!batchnorm_readvariableop_resource:3
%batchnorm_mul_readvariableop_resource:1
#batchnorm_readvariableop_1_resource:1
#batchnorm_readvariableop_2_resource:
identityИҐbatchnorm/ReadVariableOpҐbatchnorm/ReadVariableOp_1Ґbatchnorm/ReadVariableOp_2Ґbatchnorm/mul/ReadVariableOpv
batchnorm/ReadVariableOpReadVariableOp!batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0T
batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:w
batchnorm/addAddV2 batchnorm/ReadVariableOp:value:0batchnorm/add/y:output:0*
T0*
_output_shapes
:P
batchnorm/RsqrtRsqrtbatchnorm/add:z:0*
T0*
_output_shapes
:~
batchnorm/mul/ReadVariableOpReadVariableOp%batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0t
batchnorm/mulMulbatchnorm/Rsqrt:y:0$batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:c
batchnorm/mul_1Mulinputsbatchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
batchnorm/ReadVariableOp_1ReadVariableOp#batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0r
batchnorm/mul_2Mul"batchnorm/ReadVariableOp_1:value:0batchnorm/mul:z:0*
T0*
_output_shapes
:z
batchnorm/ReadVariableOp_2ReadVariableOp#batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0r
batchnorm/subSub"batchnorm/ReadVariableOp_2:value:0batchnorm/mul_2:z:0*
T0*
_output_shapes
:r
batchnorm/add_1AddV2batchnorm/mul_1:z:0batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€b
IdentityIdentitybatchnorm/add_1:z:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€Ї
NoOpNoOp^batchnorm/ReadVariableOp^batchnorm/ReadVariableOp_1^batchnorm/ReadVariableOp_2^batchnorm/mul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*.
_input_shapes
:€€€€€€€€€: : : : 24
batchnorm/ReadVariableOpbatchnorm/ReadVariableOp28
batchnorm/ReadVariableOp_1batchnorm/ReadVariableOp_128
batchnorm/ReadVariableOp_2batchnorm/ReadVariableOp_22<
batchnorm/mul/ReadVariableOpbatchnorm/mul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
г
»
&__inference_signature_wrapper_46413049
dcc
exports
net_dcd
sac
sjr	
smscg
tide
unknown
	unknown_0
	unknown_1
	unknown_2
	unknown_3
	unknown_4
	unknown_5
	unknown_6
	unknown_7
	unknown_8
	unknown_9

unknown_10

unknown_11:~

unknown_12:

unknown_13:

unknown_14:

unknown_15:

unknown_16:

unknown_17:

unknown_18:

unknown_19:

unknown_20:

unknown_21:

unknown_22:

unknown_23:

unknown_24:
identityИҐStatefulPartitionedCallЯ
StatefulPartitionedCallStatefulPartitionedCallsacexportsdccnet_dcdsjrtidesmscgunknown	unknown_0	unknown_1	unknown_2	unknown_3	unknown_4	unknown_5	unknown_6	unknown_7	unknown_8	unknown_9
unknown_10
unknown_11
unknown_12
unknown_13
unknown_14
unknown_15
unknown_16
unknown_17
unknown_18
unknown_19
unknown_20
unknown_21
unknown_22
unknown_23
unknown_24*,
Tin%
#2!*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*0
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *,
f'R%
#__inference__wrapped_model_46411640o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 22
StatefulPartitionedCallStatefulPartitionedCall:L H
'
_output_shapes
:€€€€€€€€€

_user_specified_namedcc:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	exports:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	net_dcd:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesac:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesjr:NJ
'
_output_shapes
:€€€€€€€€€

_user_specified_namesmscg:MI
'
_output_shapes
:€€€€€€€€€

_user_specified_nametide:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
Бx
¶
E__inference_model_3_layer_call_and_return_conditional_losses_46412839
sac
exports
dcc
net_dcd
sjr
tide	
smscg
normalization_18_sub_y
normalization_18_sqrt_x
normalization_19_sub_y
normalization_19_sqrt_x
normalization_20_sub_y
normalization_20_sqrt_x
normalization_21_sub_y
normalization_21_sqrt_x
normalization_22_sub_y
normalization_22_sqrt_x
normalization_23_sub_y
normalization_23_sqrt_x8
&dense_3_matmul_readvariableop_resource:~5
'dense_3_biasadd_readvariableop_resource:E
7batch_normalization_3_batchnorm_readvariableop_resource:I
;batch_normalization_3_batchnorm_mul_readvariableop_resource:G
9batch_normalization_3_batchnorm_readvariableop_1_resource:G
9batch_normalization_3_batchnorm_readvariableop_2_resource:7
%hidden_matmul_readvariableop_resource:4
&hidden_biasadd_readvariableop_resource:?
1batch_normalize_batchnorm_readvariableop_resource:C
5batch_normalize_batchnorm_mul_readvariableop_resource:A
3batch_normalize_batchnorm_readvariableop_1_resource:A
3batch_normalize_batchnorm_readvariableop_2_resource:3
!ec_matmul_readvariableop_resource:0
"ec_biasadd_readvariableop_resource:
identityИҐ.batch_normalization_3/batchnorm/ReadVariableOpҐ0batch_normalization_3/batchnorm/ReadVariableOp_1Ґ0batch_normalization_3/batchnorm/ReadVariableOp_2Ґ2batch_normalization_3/batchnorm/mul/ReadVariableOpҐ(batch_normalize/batchnorm/ReadVariableOpҐ*batch_normalize/batchnorm/ReadVariableOp_1Ґ*batch_normalize/batchnorm/ReadVariableOp_2Ґ,batch_normalize/batchnorm/mul/ReadVariableOpҐdense_3/BiasAdd/ReadVariableOpҐdense_3/MatMul/ReadVariableOpҐec/BiasAdd/ReadVariableOpҐec/MatMul/ReadVariableOpҐhidden/BiasAdd/ReadVariableOpҐhidden/MatMul/ReadVariableOpW
rescaling_3/Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7Y
rescaling_3/Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    j
rescaling_3/mulMulsacrescaling_3/Cast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€~
rescaling_3/addAddV2rescaling_3/mul:z:0rescaling_3/Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_18/subSubexportsnormalization_18_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_18/SqrtSqrtnormalization_18_sqrt_x*
T0*
_output_shapes

:_
normalization_18/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_18/MaximumMaximumnormalization_18/Sqrt:y:0#normalization_18/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_18/truedivRealDivnormalization_18/sub:z:0normalization_18/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_19/subSubdccnormalization_19_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_19/SqrtSqrtnormalization_19_sqrt_x*
T0*
_output_shapes

:_
normalization_19/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_19/MaximumMaximumnormalization_19/Sqrt:y:0#normalization_19/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_19/truedivRealDivnormalization_19/sub:z:0normalization_19/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€n
normalization_20/subSubnet_dcdnormalization_20_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_20/SqrtSqrtnormalization_20_sqrt_x*
T0*
_output_shapes

:_
normalization_20/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_20/MaximumMaximumnormalization_20/Sqrt:y:0#normalization_20/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_20/truedivRealDivnormalization_20/sub:z:0normalization_20/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€j
normalization_21/subSubsjrnormalization_21_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_21/SqrtSqrtnormalization_21_sqrt_x*
T0*
_output_shapes

:_
normalization_21/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_21/MaximumMaximumnormalization_21/Sqrt:y:0#normalization_21/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_21/truedivRealDivnormalization_21/sub:z:0normalization_21/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€k
normalization_22/subSubtidenormalization_22_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_22/SqrtSqrtnormalization_22_sqrt_x*
T0*
_output_shapes

:_
normalization_22/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_22/MaximumMaximumnormalization_22/Sqrt:y:0#normalization_22/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_22/truedivRealDivnormalization_22/sub:z:0normalization_22/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€l
normalization_23/subSubsmscgnormalization_23_sub_y*
T0*'
_output_shapes
:€€€€€€€€€_
normalization_23/SqrtSqrtnormalization_23_sqrt_x*
T0*
_output_shapes

:_
normalization_23/Maximum/yConst*
_output_shapes
: *
dtype0*
valueB
 *Хњ÷3М
normalization_23/MaximumMaximumnormalization_23/Sqrt:y:0#normalization_23/Maximum/y:output:0*
T0*
_output_shapes

:Н
normalization_23/truedivRealDivnormalization_23/sub:z:0normalization_23/Maximum:z:0*
T0*'
_output_shapes
:€€€€€€€€€[
concatenate_3/concat/axisConst*
_output_shapes
: *
dtype0*
value	B :»
concatenate_3/concatConcatV2rescaling_3/add:z:0normalization_18/truediv:z:0normalization_19/truediv:z:0normalization_20/truediv:z:0normalization_21/truediv:z:0normalization_22/truediv:z:0normalization_23/truediv:z:0"concatenate_3/concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~Д
dense_3/MatMul/ReadVariableOpReadVariableOp&dense_3_matmul_readvariableop_resource*
_output_shapes

:~*
dtype0Р
dense_3/MatMulMatMulconcatenate_3/concat:output:0%dense_3/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€В
dense_3/BiasAdd/ReadVariableOpReadVariableOp'dense_3_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0О
dense_3/BiasAddBiasAdddense_3/MatMul:product:0&dense_3/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€f
dense_3/SigmoidSigmoiddense_3/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ґ
.batch_normalization_3/batchnorm/ReadVariableOpReadVariableOp7batch_normalization_3_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0j
%batch_normalization_3/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:є
#batch_normalization_3/batchnorm/addAddV26batch_normalization_3/batchnorm/ReadVariableOp:value:0.batch_normalization_3/batchnorm/add/y:output:0*
T0*
_output_shapes
:|
%batch_normalization_3/batchnorm/RsqrtRsqrt'batch_normalization_3/batchnorm/add:z:0*
T0*
_output_shapes
:™
2batch_normalization_3/batchnorm/mul/ReadVariableOpReadVariableOp;batch_normalization_3_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0ґ
#batch_normalization_3/batchnorm/mulMul)batch_normalization_3/batchnorm/Rsqrt:y:0:batch_normalization_3/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:Ь
%batch_normalization_3/batchnorm/mul_1Muldense_3/Sigmoid:y:0'batch_normalization_3/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€¶
0batch_normalization_3/batchnorm/ReadVariableOp_1ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0і
%batch_normalization_3/batchnorm/mul_2Mul8batch_normalization_3/batchnorm/ReadVariableOp_1:value:0'batch_normalization_3/batchnorm/mul:z:0*
T0*
_output_shapes
:¶
0batch_normalization_3/batchnorm/ReadVariableOp_2ReadVariableOp9batch_normalization_3_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0і
#batch_normalization_3/batchnorm/subSub8batch_normalization_3/batchnorm/ReadVariableOp_2:value:0)batch_normalization_3/batchnorm/mul_2:z:0*
T0*
_output_shapes
:і
%batch_normalization_3/batchnorm/add_1AddV2)batch_normalization_3/batchnorm/mul_1:z:0'batch_normalization_3/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€В
hidden/MatMul/ReadVariableOpReadVariableOp%hidden_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Ъ
hidden/MatMulMatMul)batch_normalization_3/batchnorm/add_1:z:0$hidden/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€А
hidden/BiasAdd/ReadVariableOpReadVariableOp&hidden_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0Л
hidden/BiasAddBiasAddhidden/MatMul:product:0%hidden/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€d
hidden/SigmoidSigmoidhidden/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ц
(batch_normalize/batchnorm/ReadVariableOpReadVariableOp1batch_normalize_batchnorm_readvariableop_resource*
_output_shapes
:*
dtype0d
batch_normalize/batchnorm/add/yConst*
_output_shapes
: *
dtype0*
valueB
 *oГ:І
batch_normalize/batchnorm/addAddV20batch_normalize/batchnorm/ReadVariableOp:value:0(batch_normalize/batchnorm/add/y:output:0*
T0*
_output_shapes
:p
batch_normalize/batchnorm/RsqrtRsqrt!batch_normalize/batchnorm/add:z:0*
T0*
_output_shapes
:Ю
,batch_normalize/batchnorm/mul/ReadVariableOpReadVariableOp5batch_normalize_batchnorm_mul_readvariableop_resource*
_output_shapes
:*
dtype0§
batch_normalize/batchnorm/mulMul#batch_normalize/batchnorm/Rsqrt:y:04batch_normalize/batchnorm/mul/ReadVariableOp:value:0*
T0*
_output_shapes
:П
batch_normalize/batchnorm/mul_1Mulhidden/Sigmoid:y:0!batch_normalize/batchnorm/mul:z:0*
T0*'
_output_shapes
:€€€€€€€€€Ъ
*batch_normalize/batchnorm/ReadVariableOp_1ReadVariableOp3batch_normalize_batchnorm_readvariableop_1_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/mul_2Mul2batch_normalize/batchnorm/ReadVariableOp_1:value:0!batch_normalize/batchnorm/mul:z:0*
T0*
_output_shapes
:Ъ
*batch_normalize/batchnorm/ReadVariableOp_2ReadVariableOp3batch_normalize_batchnorm_readvariableop_2_resource*
_output_shapes
:*
dtype0Ґ
batch_normalize/batchnorm/subSub2batch_normalize/batchnorm/ReadVariableOp_2:value:0#batch_normalize/batchnorm/mul_2:z:0*
T0*
_output_shapes
:Ґ
batch_normalize/batchnorm/add_1AddV2#batch_normalize/batchnorm/mul_1:z:0!batch_normalize/batchnorm/sub:z:0*
T0*'
_output_shapes
:€€€€€€€€€z
ec/MatMul/ReadVariableOpReadVariableOp!ec_matmul_readvariableop_resource*
_output_shapes

:*
dtype0М
	ec/MatMulMatMul#batch_normalize/batchnorm/add_1:z:0 ec/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
ec/BiasAdd/ReadVariableOpReadVariableOp"ec_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0

ec/BiasAddBiasAddec/MatMul:product:0!ec/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
ec/ReluReluec/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€d
IdentityIdentityec/Relu:activations:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€э
NoOpNoOp/^batch_normalization_3/batchnorm/ReadVariableOp1^batch_normalization_3/batchnorm/ReadVariableOp_11^batch_normalization_3/batchnorm/ReadVariableOp_23^batch_normalization_3/batchnorm/mul/ReadVariableOp)^batch_normalize/batchnorm/ReadVariableOp+^batch_normalize/batchnorm/ReadVariableOp_1+^batch_normalize/batchnorm/ReadVariableOp_2-^batch_normalize/batchnorm/mul/ReadVariableOp^dense_3/BiasAdd/ReadVariableOp^dense_3/MatMul/ReadVariableOp^ec/BiasAdd/ReadVariableOp^ec/MatMul/ReadVariableOp^hidden/BiasAdd/ReadVariableOp^hidden/MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ѓ
_input_shapesЬ
Щ:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€::::::::::::: : : : : : : : : : : : : : 2`
.batch_normalization_3/batchnorm/ReadVariableOp.batch_normalization_3/batchnorm/ReadVariableOp2d
0batch_normalization_3/batchnorm/ReadVariableOp_10batch_normalization_3/batchnorm/ReadVariableOp_12d
0batch_normalization_3/batchnorm/ReadVariableOp_20batch_normalization_3/batchnorm/ReadVariableOp_22h
2batch_normalization_3/batchnorm/mul/ReadVariableOp2batch_normalization_3/batchnorm/mul/ReadVariableOp2T
(batch_normalize/batchnorm/ReadVariableOp(batch_normalize/batchnorm/ReadVariableOp2X
*batch_normalize/batchnorm/ReadVariableOp_1*batch_normalize/batchnorm/ReadVariableOp_12X
*batch_normalize/batchnorm/ReadVariableOp_2*batch_normalize/batchnorm/ReadVariableOp_22\
,batch_normalize/batchnorm/mul/ReadVariableOp,batch_normalize/batchnorm/mul/ReadVariableOp2@
dense_3/BiasAdd/ReadVariableOpdense_3/BiasAdd/ReadVariableOp2>
dense_3/MatMul/ReadVariableOpdense_3/MatMul/ReadVariableOp26
ec/BiasAdd/ReadVariableOpec/BiasAdd/ReadVariableOp24
ec/MatMul/ReadVariableOpec/MatMul/ReadVariableOp2>
hidden/BiasAdd/ReadVariableOphidden/BiasAdd/ReadVariableOp2<
hidden/MatMul/ReadVariableOphidden/MatMul/ReadVariableOp:L H
'
_output_shapes
:€€€€€€€€€

_user_specified_namesac:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	exports:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namedcc:PL
'
_output_shapes
:€€€€€€€€€
!
_user_specified_name	net_dcd:LH
'
_output_shapes
:€€€€€€€€€

_user_specified_namesjr:MI
'
_output_shapes
:€€€€€€€€€

_user_specified_nametide:NJ
'
_output_shapes
:€€€€€€€€€

_user_specified_namesmscg:$ 

_output_shapes

::$ 

_output_shapes

::$	 

_output_shapes

::$
 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

::$ 

_output_shapes

:
§

Ґ
0__inference_concatenate_3_layer_call_fn_46413577
inputs_0
inputs_1
inputs_2
inputs_3
inputs_4
inputs_5
inputs_6
identityM
concat/axisConst*
_output_shapes
: *
dtype0*
value	B :©
concatConcatV2inputs_0inputs_1inputs_2inputs_3inputs_4inputs_5inputs_6concat/axis:output:0*
N*
T0*'
_output_shapes
:€€€€€€€€€~W
IdentityIdentityconcat:output:0*
T0*'
_output_shapes
:€€€€€€€€€~"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*Ъ
_input_shapesИ
Е:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:€€€€€€€€€:Q M
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/0:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/1:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/2:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/3:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/4:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/5:QM
'
_output_shapes
:€€€€€€€€€
"
_user_specified_name
inputs/6
Ѓ
J
.__inference_rescaling_3_layer_call_fn_46413557

inputs
identityK
Cast/xConst*
_output_shapes
: *
dtype0*
valueB
 *Ј—7M
Cast_1/xConst*
_output_shapes
: *
dtype0*
valueB
 *    U
mulMulinputsCast/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
addAddV2mul:z:0Cast_1/x:output:0*
T0*'
_output_shapes
:€€€€€€€€€O
IdentityIdentityadd:z:0*
T0*'
_output_shapes
:€€€€€€€€€"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*&
_input_shapes
:€€€€€€€€€:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs"В-
saver_filename:0
Identity:0Identity_628"
saved_model_main_op

NoOp*>
__saved_model_init_op%#
__saved_model_init_op

NoOp*с
serving_defaultЁ
3
dcc,
serving_default_dcc:0€€€€€€€€€
;
exports0
serving_default_exports:0€€€€€€€€€
;
net_dcd0
serving_default_net_dcd:0€€€€€€€€€
3
sac,
serving_default_sac:0€€€€€€€€€
3
sjr,
serving_default_sjr:0€€€€€€€€€
7
smscg.
serving_default_smscg:0€€€€€€€€€
5
tide-
serving_default_tide:0€€€€€€€€€6
ec0
StatefulPartitionedCall:0€€€€€€€€€tensorflow/serving/predict:ѕЌ
У
layer-0
layer-1
layer-2
layer-3
layer-4
layer-5
layer-6
layer-7
	layer_with_weights-0
	layer-8

layer_with_weights-1

layer-9
layer_with_weights-2
layer-10
layer_with_weights-3
layer-11
layer_with_weights-4
layer-12
layer_with_weights-5
layer-13
layer-14
layer_with_weights-6
layer-15
layer_with_weights-7
layer-16
layer_with_weights-8
layer-17
layer_with_weights-9
layer-18
layer_with_weights-10
layer-19
	optimizer
	variables
trainable_variables
regularization_losses
	keras_api

signatures
√__call__
+ƒ&call_and_return_all_conditional_losses
≈_default_save_signature"
_tf_keras_network
"
_tf_keras_input_layer
"
_tf_keras_input_layer
"
_tf_keras_input_layer
"
_tf_keras_input_layer
"
_tf_keras_input_layer
"
_tf_keras_input_layer
"
_tf_keras_input_layer
І
	variables
trainable_variables
regularization_losses
	keras_api
∆__call__
+«&call_and_return_all_conditional_losses"
_tf_keras_layer
‘

_keep_axis
 _reduce_axis
!_reduce_axis_mask
"_broadcast_shape
#mean
#
adapt_mean
$variance
$adapt_variance
	%count
&	keras_api
»_adapt_function"
_tf_keras_layer
‘
'
_keep_axis
(_reduce_axis
)_reduce_axis_mask
*_broadcast_shape
+mean
+
adapt_mean
,variance
,adapt_variance
	-count
.	keras_api
…_adapt_function"
_tf_keras_layer
‘
/
_keep_axis
0_reduce_axis
1_reduce_axis_mask
2_broadcast_shape
3mean
3
adapt_mean
4variance
4adapt_variance
	5count
6	keras_api
 _adapt_function"
_tf_keras_layer
‘
7
_keep_axis
8_reduce_axis
9_reduce_axis_mask
:_broadcast_shape
;mean
;
adapt_mean
<variance
<adapt_variance
	=count
>	keras_api
Ћ_adapt_function"
_tf_keras_layer
‘
?
_keep_axis
@_reduce_axis
A_reduce_axis_mask
B_broadcast_shape
Cmean
C
adapt_mean
Dvariance
Dadapt_variance
	Ecount
F	keras_api
ћ_adapt_function"
_tf_keras_layer
‘
G
_keep_axis
H_reduce_axis
I_reduce_axis_mask
J_broadcast_shape
Kmean
K
adapt_mean
Lvariance
Ladapt_variance
	Mcount
N	keras_api
Ќ_adapt_function"
_tf_keras_layer
І
O	variables
Ptrainable_variables
Qregularization_losses
R	keras_api
ќ__call__
+ѕ&call_and_return_all_conditional_losses"
_tf_keras_layer
љ

Skernel
Tbias
U	variables
Vtrainable_variables
Wregularization_losses
X	keras_api
–__call__
+—&call_and_return_all_conditional_losses"
_tf_keras_layer
м
Yaxis
	Zgamma
[beta
\moving_mean
]moving_variance
^	variables
_trainable_variables
`regularization_losses
a	keras_api
“__call__
+”&call_and_return_all_conditional_losses"
_tf_keras_layer
љ

bkernel
cbias
d	variables
etrainable_variables
fregularization_losses
g	keras_api
‘__call__
+’&call_and_return_all_conditional_losses"
_tf_keras_layer
м
haxis
	igamma
jbeta
kmoving_mean
lmoving_variance
m	variables
ntrainable_variables
oregularization_losses
p	keras_api
÷__call__
+„&call_and_return_all_conditional_losses"
_tf_keras_layer
љ

qkernel
rbias
s	variables
ttrainable_variables
uregularization_losses
v	keras_api
Ў__call__
+ў&call_and_return_all_conditional_losses"
_tf_keras_layer
Ы
witer

xbeta_1

ybeta_2
	zdecay
{learning_rateSmѓTm∞Zm±[m≤bm≥cmіimµjmґqmЈrmЄSvєTvЇZvї[vЉbvљcvЊivњjvјqvЅrv¬"
	optimizer
Ц
#0
$1
%2
+3
,4
-5
36
47
58
;9
<10
=11
C12
D13
E14
K15
L16
M17
S18
T19
Z20
[21
\22
]23
b24
c25
i26
j27
k28
l29
q30
r31"
trackable_list_wrapper
f
S0
T1
Z2
[3
b4
c5
i6
j7
q8
r9"
trackable_list_wrapper
 "
trackable_list_wrapper
ѕ
|non_trainable_variables

}layers
~metrics
layer_regularization_losses
Аlayer_metrics
	variables
trainable_variables
regularization_losses
√__call__
≈_default_save_signature
+ƒ&call_and_return_all_conditional_losses
'ƒ"call_and_return_conditional_losses"
_generic_user_object
-
Џserving_default"
signature_map
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Бnon_trainable_variables
Вlayers
Гmetrics
 Дlayer_regularization_losses
Еlayer_metrics
	variables
trainable_variables
regularization_losses
∆__call__
+«&call_and_return_all_conditional_losses
'«"call_and_return_conditional_losses"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
:
 2mean
: 2variance
:	 2count
"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
:
 2mean
: 2variance
:	 2count
"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
:
 2mean
: 2variance
:	 2count
"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
:
 2mean
: 2variance
:	 2count
"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
:
 2mean
: 2variance
:	 2count
"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
:
 2mean
: 2variance
:	 2count
"
_generic_user_object
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Жnon_trainable_variables
Зlayers
Иmetrics
 Йlayer_regularization_losses
Кlayer_metrics
O	variables
Ptrainable_variables
Qregularization_losses
ќ__call__
+ѕ&call_and_return_all_conditional_losses
'ѕ"call_and_return_conditional_losses"
_generic_user_object
 :~2dense_3/kernel
:2dense_3/bias
.
S0
T1"
trackable_list_wrapper
.
S0
T1"
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Лnon_trainable_variables
Мlayers
Нmetrics
 Оlayer_regularization_losses
Пlayer_metrics
U	variables
Vtrainable_variables
Wregularization_losses
–__call__
+—&call_and_return_all_conditional_losses
'—"call_and_return_conditional_losses"
_generic_user_object
 "
trackable_list_wrapper
):'2batch_normalization_3/gamma
(:&2batch_normalization_3/beta
1:/ (2!batch_normalization_3/moving_mean
5:3 (2%batch_normalization_3/moving_variance
<
Z0
[1
\2
]3"
trackable_list_wrapper
.
Z0
[1"
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Рnon_trainable_variables
Сlayers
Тmetrics
 Уlayer_regularization_losses
Фlayer_metrics
^	variables
_trainable_variables
`regularization_losses
“__call__
+”&call_and_return_all_conditional_losses
'”"call_and_return_conditional_losses"
_generic_user_object
:2hidden/kernel
:2hidden/bias
.
b0
c1"
trackable_list_wrapper
.
b0
c1"
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Хnon_trainable_variables
Цlayers
Чmetrics
 Шlayer_regularization_losses
Щlayer_metrics
d	variables
etrainable_variables
fregularization_losses
‘__call__
+’&call_and_return_all_conditional_losses
'’"call_and_return_conditional_losses"
_generic_user_object
 "
trackable_list_wrapper
#:!2batch_normalize/gamma
": 2batch_normalize/beta
+:) (2batch_normalize/moving_mean
/:- (2batch_normalize/moving_variance
<
i0
j1
k2
l3"
trackable_list_wrapper
.
i0
j1"
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Ъnon_trainable_variables
Ыlayers
Ьmetrics
 Эlayer_regularization_losses
Юlayer_metrics
m	variables
ntrainable_variables
oregularization_losses
÷__call__
+„&call_and_return_all_conditional_losses
'„"call_and_return_conditional_losses"
_generic_user_object
:2	ec/kernel
:2ec/bias
.
q0
r1"
trackable_list_wrapper
.
q0
r1"
trackable_list_wrapper
 "
trackable_list_wrapper
µ
Яnon_trainable_variables
†layers
°metrics
 Ґlayer_regularization_losses
£layer_metrics
s	variables
ttrainable_variables
uregularization_losses
Ў__call__
+ў&call_and_return_all_conditional_losses
'ў"call_and_return_conditional_losses"
_generic_user_object
:	 (2Adamax/iter
: (2Adamax/beta_1
: (2Adamax/beta_2
: (2Adamax/decay
: (2Adamax/learning_rate
∆
#0
$1
%2
+3
,4
-5
36
47
58
;9
<10
=11
C12
D13
E14
K15
L16
M17
\18
]19
k20
l21"
trackable_list_wrapper
ґ
0
1
2
3
4
5
6
7
	8

9
10
11
12
13
14
15
16
17
18
19"
trackable_list_wrapper
0
§0
•1"
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
.
\0
]1"
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
.
k0
l1"
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
R

¶total

Іcount
®	variables
©	keras_api"
_tf_keras_metric
c

™total

Ђcount
ђ
_fn_kwargs
≠	variables
Ѓ	keras_api"
_tf_keras_metric
:  (2total
:  (2count
0
¶0
І1"
trackable_list_wrapper
.
®	variables"
_generic_user_object
:  (2total
:  (2count
 "
trackable_dict_wrapper
0
™0
Ђ1"
trackable_list_wrapper
.
≠	variables"
_generic_user_object
':%~2Adamax/dense_3/kernel/m
!:2Adamax/dense_3/bias/m
0:.2$Adamax/batch_normalization_3/gamma/m
/:-2#Adamax/batch_normalization_3/beta/m
&:$2Adamax/hidden/kernel/m
 :2Adamax/hidden/bias/m
*:(2Adamax/batch_normalize/gamma/m
):'2Adamax/batch_normalize/beta/m
": 2Adamax/ec/kernel/m
:2Adamax/ec/bias/m
':%~2Adamax/dense_3/kernel/v
!:2Adamax/dense_3/bias/v
0:.2$Adamax/batch_normalization_3/gamma/v
/:-2#Adamax/batch_normalization_3/beta/v
&:$2Adamax/hidden/kernel/v
 :2Adamax/hidden/bias/v
*:(2Adamax/batch_normalize/gamma/v
):'2Adamax/batch_normalize/beta/v
": 2Adamax/ec/kernel/v
:2Adamax/ec/bias/v
ц2у
*__inference_model_3_layer_call_fn_46411972
*__inference_model_3_layer_call_fn_46413160
*__inference_model_3_layer_call_fn_46413299
*__inference_model_3_layer_call_fn_46412728ј
Ј≤≥
FullArgSpec1
args)Ъ&
jself
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsЪ
p 

 

kwonlyargsЪ 
kwonlydefaults™ 
annotations™ *
 
в2я
E__inference_model_3_layer_call_and_return_conditional_losses_46413410
E__inference_model_3_layer_call_and_return_conditional_losses_46413549
E__inference_model_3_layer_call_and_return_conditional_losses_46412839
E__inference_model_3_layer_call_and_return_conditional_losses_46412978ј
Ј≤≥
FullArgSpec1
args)Ъ&
jself
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsЪ
p 

 

kwonlyargsЪ 
kwonlydefaults™ 
annotations™ *
 
уBр
#__inference__wrapped_model_46411640sacexportsdccnet_dcdsjrtidesmscg"Ш
С≤Н
FullArgSpec
argsЪ 
varargsjargs
varkwjkwargs
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
Ў2’
.__inference_rescaling_3_layer_call_fn_46413557Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
у2р
I__inference_rescaling_3_layer_call_and_return_conditional_losses_46413565Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
†2ЭЪ
У≤П
FullArgSpec
argsЪ

jiterator
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
†2ЭЪ
У≤П
FullArgSpec
argsЪ

jiterator
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
†2ЭЪ
У≤П
FullArgSpec
argsЪ

jiterator
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
†2ЭЪ
У≤П
FullArgSpec
argsЪ

jiterator
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
†2ЭЪ
У≤П
FullArgSpec
argsЪ

jiterator
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
†2ЭЪ
У≤П
FullArgSpec
argsЪ

jiterator
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
Џ2„
0__inference_concatenate_3_layer_call_fn_46413577Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
х2т
K__inference_concatenate_3_layer_call_and_return_conditional_losses_46413589Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
‘2—
*__inference_dense_3_layer_call_fn_46413600Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
п2м
E__inference_dense_3_layer_call_and_return_conditional_losses_46413611Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
Ѓ2Ђ
8__inference_batch_normalization_3_layer_call_fn_46413631
8__inference_batch_normalization_3_layer_call_fn_46413665і
Ђ≤І
FullArgSpec)
args!Ъ
jself
jinputs

jtraining
varargs
 
varkw
 
defaultsЪ
p 

kwonlyargsЪ 
kwonlydefaults™ 
annotations™ *
 
д2б
S__inference_batch_normalization_3_layer_call_and_return_conditional_losses_46413685
S__inference_batch_normalization_3_layer_call_and_return_conditional_losses_46413719і
Ђ≤І
FullArgSpec)
args!Ъ
jself
jinputs

jtraining
varargs
 
varkw
 
defaultsЪ
p 

kwonlyargsЪ 
kwonlydefaults™ 
annotations™ *
 
”2–
)__inference_hidden_layer_call_fn_46413730Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
о2л
D__inference_hidden_layer_call_and_return_conditional_losses_46413741Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
Ґ2Я
2__inference_batch_normalize_layer_call_fn_46413761
2__inference_batch_normalize_layer_call_fn_46413795і
Ђ≤І
FullArgSpec)
args!Ъ
jself
jinputs

jtraining
varargs
 
varkw
 
defaultsЪ
p 

kwonlyargsЪ 
kwonlydefaults™ 
annotations™ *
 
Ў2’
M__inference_batch_normalize_layer_call_and_return_conditional_losses_46413815
M__inference_batch_normalize_layer_call_and_return_conditional_losses_46413849і
Ђ≤І
FullArgSpec)
args!Ъ
jself
jinputs

jtraining
varargs
 
varkw
 
defaultsЪ
p 

kwonlyargsЪ 
kwonlydefaults™ 
annotations™ *
 
ѕ2ћ
%__inference_ec_layer_call_fn_46413860Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
к2з
@__inference_ec_layer_call_and_return_conditional_losses_46413871Ґ
Щ≤Х
FullArgSpec
argsЪ
jself
jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
рBн
&__inference_signature_wrapper_46413049dccexportsnet_dcdsacsjrsmscgtide"Ф
Н≤Й
FullArgSpec
argsЪ 
varargs
 
varkwjkwargs
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
	J
Const
J	
Const_1
J	
Const_2
J	
Const_3
J	
Const_4
J	
Const_5
J	
Const_6
J	
Const_7
J	
Const_8
J	
Const_9
J

Const_10
J

Const_11ч
#__inference__wrapped_model_46411640ѕ&џ№ЁёяабвгдежST]Z\[bclikjqrыҐч
пҐл
иЪд
К
sac€€€€€€€€€
!К
exports€€€€€€€€€
К
dcc€€€€€€€€€
!К
net_dcd€€€€€€€€€
К
sjr€€€€€€€€€
К
tide€€€€€€€€€
К
smscg€€€€€€€€€
™ "'™$
"
ecК
ec€€€€€€€€€є
S__inference_batch_normalization_3_layer_call_and_return_conditional_losses_46413685b]Z\[3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p 
™ "%Ґ"
К
0€€€€€€€€€
Ъ є
S__inference_batch_normalization_3_layer_call_and_return_conditional_losses_46413719b\]Z[3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p
™ "%Ґ"
К
0€€€€€€€€€
Ъ С
8__inference_batch_normalization_3_layer_call_fn_46413631U]Z\[3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p 
™ "К€€€€€€€€€С
8__inference_batch_normalization_3_layer_call_fn_46413665U\]Z[3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p
™ "К€€€€€€€€€≥
M__inference_batch_normalize_layer_call_and_return_conditional_losses_46413815blikj3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p 
™ "%Ґ"
К
0€€€€€€€€€
Ъ ≥
M__inference_batch_normalize_layer_call_and_return_conditional_losses_46413849bklij3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p
™ "%Ґ"
К
0€€€€€€€€€
Ъ Л
2__inference_batch_normalize_layer_call_fn_46413761Ulikj3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p 
™ "К€€€€€€€€€Л
2__inference_batch_normalize_layer_call_fn_46413795Uklij3Ґ0
)Ґ&
 К
inputs€€€€€€€€€
p
™ "К€€€€€€€€€Н
K__inference_concatenate_3_layer_call_and_return_conditional_losses_46413589љУҐП
ЗҐГ
АЪь
"К
inputs/0€€€€€€€€€
"К
inputs/1€€€€€€€€€
"К
inputs/2€€€€€€€€€
"К
inputs/3€€€€€€€€€
"К
inputs/4€€€€€€€€€
"К
inputs/5€€€€€€€€€
"К
inputs/6€€€€€€€€€
™ "%Ґ"
К
0€€€€€€€€€~
Ъ е
0__inference_concatenate_3_layer_call_fn_46413577∞УҐП
ЗҐГ
АЪь
"К
inputs/0€€€€€€€€€
"К
inputs/1€€€€€€€€€
"К
inputs/2€€€€€€€€€
"К
inputs/3€€€€€€€€€
"К
inputs/4€€€€€€€€€
"К
inputs/5€€€€€€€€€
"К
inputs/6€€€€€€€€€
™ "К€€€€€€€€€~•
E__inference_dense_3_layer_call_and_return_conditional_losses_46413611\ST/Ґ,
%Ґ"
 К
inputs€€€€€€€€€~
™ "%Ґ"
К
0€€€€€€€€€
Ъ }
*__inference_dense_3_layer_call_fn_46413600OST/Ґ,
%Ґ"
 К
inputs€€€€€€€€€~
™ "К€€€€€€€€€†
@__inference_ec_layer_call_and_return_conditional_losses_46413871\qr/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "%Ґ"
К
0€€€€€€€€€
Ъ x
%__inference_ec_layer_call_fn_46413860Oqr/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "К€€€€€€€€€§
D__inference_hidden_layer_call_and_return_conditional_losses_46413741\bc/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "%Ґ"
К
0€€€€€€€€€
Ъ |
)__inference_hidden_layer_call_fn_46413730Obc/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "К€€€€€€€€€Я
E__inference_model_3_layer_call_and_return_conditional_losses_46412839’&џ№ЁёяабвгдежST]Z\[bclikjqrГҐ€
чҐу
иЪд
К
sac€€€€€€€€€
!К
exports€€€€€€€€€
К
dcc€€€€€€€€€
!К
net_dcd€€€€€€€€€
К
sjr€€€€€€€€€
К
tide€€€€€€€€€
К
smscg€€€€€€€€€
p 

 
™ "%Ґ"
К
0€€€€€€€€€
Ъ Я
E__inference_model_3_layer_call_and_return_conditional_losses_46412978’&џ№ЁёяабвгдежST\]Z[bcklijqrГҐ€
чҐу
иЪд
К
sac€€€€€€€€€
!К
exports€€€€€€€€€
К
dcc€€€€€€€€€
!К
net_dcd€€€€€€€€€
К
sjr€€€€€€€€€
К
tide€€€€€€€€€
К
smscg€€€€€€€€€
p

 
™ "%Ґ"
К
0€€€€€€€€€
Ъ Ј
E__inference_model_3_layer_call_and_return_conditional_losses_46413410н&џ№ЁёяабвгдежST]Z\[bclikjqrЫҐЧ
ПҐЛ
АЪь
"К
inputs/0€€€€€€€€€
"К
inputs/1€€€€€€€€€
"К
inputs/2€€€€€€€€€
"К
inputs/3€€€€€€€€€
"К
inputs/4€€€€€€€€€
"К
inputs/5€€€€€€€€€
"К
inputs/6€€€€€€€€€
p 

 
™ "%Ґ"
К
0€€€€€€€€€
Ъ Ј
E__inference_model_3_layer_call_and_return_conditional_losses_46413549н&џ№ЁёяабвгдежST\]Z[bcklijqrЫҐЧ
ПҐЛ
АЪь
"К
inputs/0€€€€€€€€€
"К
inputs/1€€€€€€€€€
"К
inputs/2€€€€€€€€€
"К
inputs/3€€€€€€€€€
"К
inputs/4€€€€€€€€€
"К
inputs/5€€€€€€€€€
"К
inputs/6€€€€€€€€€
p

 
™ "%Ґ"
К
0€€€€€€€€€
Ъ ч
*__inference_model_3_layer_call_fn_46411972»&џ№ЁёяабвгдежST]Z\[bclikjqrГҐ€
чҐу
иЪд
К
sac€€€€€€€€€
!К
exports€€€€€€€€€
К
dcc€€€€€€€€€
!К
net_dcd€€€€€€€€€
К
sjr€€€€€€€€€
К
tide€€€€€€€€€
К
smscg€€€€€€€€€
p 

 
™ "К€€€€€€€€€ч
*__inference_model_3_layer_call_fn_46412728»&џ№ЁёяабвгдежST\]Z[bcklijqrГҐ€
чҐу
иЪд
К
sac€€€€€€€€€
!К
exports€€€€€€€€€
К
dcc€€€€€€€€€
!К
net_dcd€€€€€€€€€
К
sjr€€€€€€€€€
К
tide€€€€€€€€€
К
smscg€€€€€€€€€
p

 
™ "К€€€€€€€€€П
*__inference_model_3_layer_call_fn_46413160а&џ№ЁёяабвгдежST]Z\[bclikjqrЫҐЧ
ПҐЛ
АЪь
"К
inputs/0€€€€€€€€€
"К
inputs/1€€€€€€€€€
"К
inputs/2€€€€€€€€€
"К
inputs/3€€€€€€€€€
"К
inputs/4€€€€€€€€€
"К
inputs/5€€€€€€€€€
"К
inputs/6€€€€€€€€€
p 

 
™ "К€€€€€€€€€П
*__inference_model_3_layer_call_fn_46413299а&џ№ЁёяабвгдежST\]Z[bcklijqrЫҐЧ
ПҐЛ
АЪь
"К
inputs/0€€€€€€€€€
"К
inputs/1€€€€€€€€€
"К
inputs/2€€€€€€€€€
"К
inputs/3€€€€€€€€€
"К
inputs/4€€€€€€€€€
"К
inputs/5€€€€€€€€€
"К
inputs/6€€€€€€€€€
p

 
™ "К€€€€€€€€€•
I__inference_rescaling_3_layer_call_and_return_conditional_losses_46413565X/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "%Ґ"
К
0€€€€€€€€€
Ъ }
.__inference_rescaling_3_layer_call_fn_46413557K/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "К€€€€€€€€€ѓ
&__inference_signature_wrapper_46413049Д&џ№ЁёяабвгдежST]Z\[bclikjqr∞Ґђ
Ґ 
§™†
$
dccК
dcc€€€€€€€€€
,
exports!К
exports€€€€€€€€€
,
net_dcd!К
net_dcd€€€€€€€€€
$
sacК
sac€€€€€€€€€
$
sjrК
sjr€€€€€€€€€
(
smscgК
smscg€€€€€€€€€
&
tideК
tide€€€€€€€€€"'™$
"
ecК
ec€€€€€€€€€