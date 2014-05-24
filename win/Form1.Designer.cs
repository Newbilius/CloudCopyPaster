namespace AndroidCopyPaster
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.buttonSave = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.notifyIconMain = new System.Windows.Forms.NotifyIcon(this.components);
            this.mouseKeyEventProvider = new MouseKeyboardActivityMonitor.Controls.MouseKeyEventProvider();
            this.progressBar = new System.Windows.Forms.ProgressBar();
            this.checkBox1 = new System.Windows.Forms.CheckBox();
            this.checkBoxShift = new System.Windows.Forms.CheckBox();
            this.checkBoxCtrl = new System.Windows.Forms.CheckBox();
            this.checkBoxAlt = new System.Windows.Forms.CheckBox();
            this.textBoxPass = new System.Windows.Forms.TextBox();
            this.textBoxLogin = new System.Windows.Forms.TextBox();
            this.textBoxKey = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // buttonSave
            // 
            resources.ApplyResources(this.buttonSave, "buttonSave");
            this.buttonSave.Name = "buttonSave";
            this.buttonSave.UseVisualStyleBackColor = true;
            this.buttonSave.Click += new System.EventHandler(this.buttonSave_Click);
            // 
            // label1
            // 
            resources.ApplyResources(this.label1, "label1");
            this.label1.Name = "label1";
            // 
            // label2
            // 
            resources.ApplyResources(this.label2, "label2");
            this.label2.Name = "label2";
            // 
            // label4
            // 
            resources.ApplyResources(this.label4, "label4");
            this.label4.Name = "label4";
            // 
            // notifyIconMain
            // 
            resources.ApplyResources(this.notifyIconMain, "notifyIconMain");
            this.notifyIconMain.Click += new System.EventHandler(this.IconClick);
            // 
            // mouseKeyEventProvider
            // 
            this.mouseKeyEventProvider.Enabled = true;
            this.mouseKeyEventProvider.HookType = MouseKeyboardActivityMonitor.Controls.HookType.Global;
            this.mouseKeyEventProvider.KeyUp += new System.Windows.Forms.KeyEventHandler(this.GlobalKeyUp);
            // 
            // progressBar
            // 
            resources.ApplyResources(this.progressBar, "progressBar");
            this.progressBar.Name = "progressBar";
            this.progressBar.Value = 60;
            // 
            // checkBox1
            // 
            resources.ApplyResources(this.checkBox1, "checkBox1");
            this.checkBox1.Checked = global::AndroidCopyPaster.Properties.Settings.Default.ShowBallon;
            this.checkBox1.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBox1.DataBindings.Add(new System.Windows.Forms.Binding("Checked", global::AndroidCopyPaster.Properties.Settings.Default, "ShowBallon", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.checkBox1.Name = "checkBox1";
            this.checkBox1.UseVisualStyleBackColor = true;
            // 
            // checkBoxShift
            // 
            resources.ApplyResources(this.checkBoxShift, "checkBoxShift");
            this.checkBoxShift.Checked = global::AndroidCopyPaster.Properties.Settings.Default.Shift;
            this.checkBoxShift.DataBindings.Add(new System.Windows.Forms.Binding("Checked", global::AndroidCopyPaster.Properties.Settings.Default, "Shift", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.checkBoxShift.Name = "checkBoxShift";
            this.checkBoxShift.UseVisualStyleBackColor = true;
            // 
            // checkBoxCtrl
            // 
            resources.ApplyResources(this.checkBoxCtrl, "checkBoxCtrl");
            this.checkBoxCtrl.Checked = global::AndroidCopyPaster.Properties.Settings.Default.Ctrl;
            this.checkBoxCtrl.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBoxCtrl.DataBindings.Add(new System.Windows.Forms.Binding("Checked", global::AndroidCopyPaster.Properties.Settings.Default, "Ctrl", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.checkBoxCtrl.Name = "checkBoxCtrl";
            this.checkBoxCtrl.UseVisualStyleBackColor = true;
            // 
            // checkBoxAlt
            // 
            resources.ApplyResources(this.checkBoxAlt, "checkBoxAlt");
            this.checkBoxAlt.Checked = global::AndroidCopyPaster.Properties.Settings.Default.Alt;
            this.checkBoxAlt.CheckState = System.Windows.Forms.CheckState.Checked;
            this.checkBoxAlt.DataBindings.Add(new System.Windows.Forms.Binding("Checked", global::AndroidCopyPaster.Properties.Settings.Default, "Alt", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.checkBoxAlt.Name = "checkBoxAlt";
            this.checkBoxAlt.UseVisualStyleBackColor = true;
            // 
            // textBoxPass
            // 
            resources.ApplyResources(this.textBoxPass, "textBoxPass");
            this.textBoxPass.DataBindings.Add(new System.Windows.Forms.Binding("Text", global::AndroidCopyPaster.Properties.Settings.Default, "Pass", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.textBoxPass.Name = "textBoxPass";
            this.textBoxPass.Text = global::AndroidCopyPaster.Properties.Settings.Default.Pass;
            this.textBoxPass.UseSystemPasswordChar = true;
            // 
            // textBoxLogin
            // 
            resources.ApplyResources(this.textBoxLogin, "textBoxLogin");
            this.textBoxLogin.DataBindings.Add(new System.Windows.Forms.Binding("Text", global::AndroidCopyPaster.Properties.Settings.Default, "Login", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.textBoxLogin.Name = "textBoxLogin";
            this.textBoxLogin.Text = global::AndroidCopyPaster.Properties.Settings.Default.Login;
            // 
            // textBoxKey
            // 
            resources.ApplyResources(this.textBoxKey, "textBoxKey");
            this.textBoxKey.CharacterCasing = System.Windows.Forms.CharacterCasing.Upper;
            this.textBoxKey.DataBindings.Add(new System.Windows.Forms.Binding("Text", global::AndroidCopyPaster.Properties.Settings.Default, "Key", true, System.Windows.Forms.DataSourceUpdateMode.OnPropertyChanged));
            this.textBoxKey.Name = "textBoxKey";
            this.textBoxKey.Text = global::AndroidCopyPaster.Properties.Settings.Default.Key;
            this.textBoxKey.KeyUp += new System.Windows.Forms.KeyEventHandler(this.textBoxKey_KeyUp);
            // 
            // Form1
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.progressBar);
            this.Controls.Add(this.checkBox1);
            this.Controls.Add(this.checkBoxShift);
            this.Controls.Add(this.checkBoxCtrl);
            this.Controls.Add(this.checkBoxAlt);
            this.Controls.Add(this.textBoxPass);
            this.Controls.Add(this.textBoxLogin);
            this.Controls.Add(this.textBoxKey);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.buttonSave);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Form1";
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonSave;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textBoxKey;
        private System.Windows.Forms.TextBox textBoxPass;
        private System.Windows.Forms.TextBox textBoxLogin;
        private System.Windows.Forms.CheckBox checkBoxAlt;
        private System.Windows.Forms.CheckBox checkBoxCtrl;
        private System.Windows.Forms.CheckBox checkBoxShift;
        private System.Windows.Forms.NotifyIcon notifyIconMain;
        private MouseKeyboardActivityMonitor.Controls.MouseKeyEventProvider mouseKeyEventProvider;
        private System.Windows.Forms.CheckBox checkBox1;
        private System.Windows.Forms.ProgressBar progressBar;
    }
}

