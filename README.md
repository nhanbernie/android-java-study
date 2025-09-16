View (lớp cơ sở cho mọi UI component)
│
├── TextView
│   ├── EditText (ô nhập văn bản)
│   ├── Button
│   ├── CheckBox
│   ├── RadioButton
│   └── Switch / ToggleButton
│
├── ImageView (hiển thị ảnh)
│
├── ProgressBar (thanh tiến trình)
│
├── SurfaceView / TextureView (render đồ họa, video)
│
├── ViewGroup (chứa các View khác → layout/container)
│   ├── LinearLayout
│   ├── RelativeLayout
│   ├── ConstraintLayout
│   ├── FrameLayout
│   └── RecyclerView (list hiện đại, dùng RecyclerView.Adapter)
│
└── AdapterView (cũng là ViewGroup, nhưng chuyên dùng Adapter)
    ├── ListView (danh sách dọc)
    ├── GridView (lưới item)
    ├── Spinner (dropdown menu)
    └── Gallery (cũ, gần giống HorizontalListView)
